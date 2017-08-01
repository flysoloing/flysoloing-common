package com.flysoloing.common.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *  包路径扫描工具类
 * @author laitao
 * @since 2017-07-13 16:25:18
 */
public class PackageScannerUtils {

    public static Set<Class<?>> getClasses(String packageName, boolean recursive) {

        // 第一个class类的集合
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        // 获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                // 获取下一个元素
                URL url = dirs.nextElement();
                // 得到协议的名称
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    System.out.println("file类型的扫描");
                    // 获取包的物理路径
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    // 以文件的方式扫描整个包下的文件 并添加到集合中
                    findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    System.out.println("jar类型的扫描");
                    JarFile jar;
                    try {
                        // 获取jar
                        jar = ((JarURLConnection) url.openConnection()).getJarFile();
                        // 从此jar包 得到一个枚举类
                        Enumeration<JarEntry> entries = jar.entries();
                        // 同样的进行循环迭代
                        while (entries.hasMoreElements()) {
                            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 如果是以/开头的
                            if (name.charAt(0) == '/') {
                                // 获取后面的字符串
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾 是一个包
                                if (idx != -1) {
                                    // 获取包名 把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去 并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件 而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        // 去掉后面的".class" 获取真正的类名
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            // 添加到classes
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            // log
                                            // .error("添加用户自定义视图类错误 找不到此类的.class文件");
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    } catch (IOException e) {
                        // log.error("在扫描用户定义视图时从jar包获取文件出错");
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }

    public static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(packagePath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            // log.warn("用户定义包名 " + packageName + " 下没有任何文件");
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirFiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
            public boolean accept(File file) {
                return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
            }
        });
        if (dirFiles == null || dirFiles.length == 0) {
            // 如果包路径下面不存在任何文件，则直接返回
            return;
        }
        // 循环所有文件
        for (File file : dirFiles) {
            // 如果是目录 则继续扫描
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
            } else {
                // 如果是java类文件 去掉后面的.class 只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    //classes.add(Class.forName(packageName + '.' + className));
                    //经过回复同学的提醒，这里用forName有一些不好，会触发static方法，没有使用classLoader的load干净
                    classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                } catch (ClassNotFoundException e) {
                    // log.error("添加用户自定义视图类错误 找不到此类的.class文件");
                    e.printStackTrace();
                }
            }
        }
    }

    public static Map<String, List<ServiceInfo>> findAnnotationInfo(Set<Class<?>> clazzSet) {
        Map<String, List<ServiceInfo>> serviceInfoMap = new HashMap<String, List<ServiceInfo>>();
        for (Class<?> clazz : clazzSet) {
            javax.ws.rs.Path path = clazz.getAnnotation(javax.ws.rs.Path.class);
            String classPath = "";
            if (path != null) {
                classPath = path.value();
            }
            Method[] methods = clazz.getMethods();
            List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
            for (Method method : methods) {
                javax.ws.rs.Path path1 = method.getAnnotation(javax.ws.rs.Path.class);
                String methodPath = "";
                if (path1 != null) {
                    methodPath = path1.value();
                }
                serviceInfos.add(buildServiceInfo(clazz.getName(), method.toString().replace("abstract ", ""), classPath + "" + methodPath));

            }
            serviceInfoMap.put(clazz.getCanonicalName(), serviceInfos);
        }

        return serviceInfoMap;
    }

    public static ServiceInfo buildServiceInfo(String className, String jsfMethod, String restMethod) {
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setClassName(className);
        serviceInfo.setJsfMethod(jsfMethod);
        serviceInfo.setRestMethod(restMethod);
        return serviceInfo;
    }

    public static void main(String[] args) {
        String adminPackageStr = "com.jd.insurance.item.export.admin";
        String webPackageStr = "com.jd.insurance.item.export.web";

        Set<Class<?>> adminClassSet = getClasses(adminPackageStr, true);
        System.out.println("admin class set size: " + adminClassSet.size());
        for (Class<?> clazz : adminClassSet) {
            System.out.println(clazz.getCanonicalName());
        }

        Set<Class<?>> webClassSet = getClasses(webPackageStr, true);
        System.out.println("web class set size: " + webClassSet.size());
        for (Class<?> clazz : webClassSet) {
            System.out.println(clazz.getCanonicalName());
        }

        Map<String, List<ServiceInfo>> serviceInfoMap = findAnnotationInfo(adminClassSet);
        for (String key : serviceInfoMap.keySet()) {
            for (ServiceInfo serviceInfo : serviceInfoMap.get(key)) {
                System.out.println(serviceInfo.toString());
            }

        }

        Map<String, List<ServiceInfo>> serviceInfoMap1 = findAnnotationInfo(webClassSet);
        for (String key : serviceInfoMap1.keySet()) {
            for (ServiceInfo serviceInfo : serviceInfoMap1.get(key)) {
                System.out.println(serviceInfo.toString());
            }
        }
    }

    static class ServiceInfo {
        private String className;

        private String jsfMethod;

        private String restMethod;

        public ServiceInfo() {
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getJsfMethod() {
            return jsfMethod;
        }

        public void setJsfMethod(String jsfMethod) {
            this.jsfMethod = jsfMethod;
        }

        public String getRestMethod() {
            return restMethod;
        }

        public void setRestMethod(String restMethod) {
            this.restMethod = restMethod;
        }

        @Override
        public String toString() {
            return "ServiceInfo{" +
                    "className='" + className + '\'' +
                    ", jsfMethod='" + jsfMethod + '\'' +
                    ", restMethod='" + restMethod + '\'' +
                    '}';
        }
    }
}
