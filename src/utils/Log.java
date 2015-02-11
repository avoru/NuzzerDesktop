package utils;

public class Log
{
    public static void i(String str1, String str2)
    {
        System.out.println(str1 + ": " + str2);
    }

    public static void e(String str1, String str2)
    {
        System.err.println(str1 + ": " + str2);
    }
}
