package com.beag;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

public class DeN
{
    public static String urlEncode(String str)
    {
        String utf8 = "This is not UTF-8";
        try
        {
            utf8 = URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return utf8;
        }
        return utf8;
    }

    public static String urlDecode(String str)
    {
        String utf8 = "This is not UTF-8";
        try
        {
            utf8 = URLDecoder.decode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            return utf8;
        }
        return utf8;
    }

    public static String base64Encode(String str)
    {
        str = DatatypeConverter.printBase64Binary(str.getBytes());
        return str;
    }

    public static String base64Decode(String str)
    {
        byte[] base64 = null;

        base64 = DatatypeConverter.parseBase64Binary(str);
        String deStr = new String(base64);

        return deStr;
    }

    public static String htmlEncode(String str)
    {
        String enStr = StringEscapeUtils.escapeHtml4(str);

        return enStr;
    }

    public static String htmlDecode(String str)
    {
        String deStr = StringEscapeUtils.unescapeHtml4(str);

        return deStr;
    }



    public static String xmlEncode(String str)
    {
        String enStr = StringEscapeUtils.escapeXml11(str);

        return enStr;
    }

    public static String xmlDecode(String str)
    {
        String deStr = StringEscapeUtils.unescapeXml(str);

        return deStr;
    }


    public static String hexEncode(String str)
    {
        byte[] enByte = str.getBytes();

        String enStr = Hex.encodeHexString(enByte);

        return enStr;
    }

    public static String hexDecode(String str)
    {
        String deStr = "This is not a Hex value";
        char[] deChar = str.toCharArray();
        byte[] deByte = str.getBytes();
        try
        {
            deByte = Hex.decodeHex(deChar);
            deStr = new String(deByte);
        }
        catch (DecoderException e)
        {
            return deStr;
        }
        return deStr;
    }

    public static String decEncode(String str)
    {
        String enStr = "";
        for (int i = 0; i < str.length(); i++)
        {
            int c = str.charAt(i);

            enStr = enStr + String.valueOf(c);
        }
        return enStr;
    }

    public static String decDecode(String str)
    {
        int dec_num = Integer.parseInt(str);

        char deChar = (char)dec_num;

        return Character.toString(deChar);
    }

    public static String utf8Encode(String str, int choice)
    {
        byte[] utf8bytes = StringUtils.getBytesUtf8(str);
        if (choice == 1) {
            str = utf8UrlFormat(utf8bytes);
        }
        if (choice == 2)
        {
            byte[] utf8_2bytes = new byte[utf8bytes.length * 2];

            int i = 0;
            for (int j = 0; i < utf8bytes.length; j++)
            {
                utf8_2bytes[j] = ((byte)(utf8bytes[i] >> 6 | 0xC0));
                utf8_2bytes[(j + 1)] = ((byte)(utf8bytes[i] & 0x3F | 0x80));
                j++;i++;
            }
            str = utf8UrlFormat(utf8_2bytes);
        }
        else if (choice == 3)
        {
            byte[] utf8_3bytes = new byte[utf8bytes.length * 3];

            int i = 0;
            for (int j = 0; i < utf8bytes.length; j++)
            {
                utf8_3bytes[j] = ((byte)(utf8bytes[i] >> 12 | 0xE0));
                utf8_3bytes[(j + 1)] = ((byte)(utf8bytes[i] >> 6 & 0x3F | 0x80));
                utf8_3bytes[(j + 2)] = ((byte)(utf8bytes[i] & 0x3F | 0x80));
                j++;i++;
            }
            str = utf8UrlFormat(utf8_3bytes);
        }
        return str;
    }

    private static String utf8UrlFormat(byte[] b)
    {
        StringBuilder sb = new StringBuilder();

        char[] c = Hex.encodeHex(b);
        for (int i = 0; i < c.length; i++)
        {
            sb.append("%").append(c[i]);
            i++;
            sb.append(c[i]);
        }
        String str = sb.toString();

        return str;
    }

    public static String unicodeEncode(char[] charArray, int choice){

        StringBuilder result = new StringBuilder();

        switch (choice) {

            case 1: for (char ch : charArray) {
                result.append("\\u00" + Integer.toHexString(ch));

            }
            break;

            case 2: for (char ch : charArray) {
                result.append("\\x" + Integer.toHexString(ch));
            }


        }

        return result.toString();

    }

    public static String unicodeDecode(String str){

        //check if string has preceding \\x then replace with \\u00 for conversion
        if(str.contains("\\x")){
            str = str.replace("\\x", "\\u00");
        }
        str = StringEscapeUtils.unescapeJava(str);
        return str;
    }
}
