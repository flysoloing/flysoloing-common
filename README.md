# flysoloing-common

## IDEA file template datetime setting

#set($str = "")
#set($stringClass=$str.getClass())

#set($date=$stringClass.forName("java.util.Date").newInstance())
#set($dateFormat=$stringClass.forName("java.text.SimpleDateFormat").getConstructor($stringClass).newInstance("yyyy-MM-dd HH:mm:ss"))
#set($fdate=$dateFormat.format($date))

/**
 *
 * @author ${USER}
 * @since ${fdate}
 */
