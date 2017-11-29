package com.beag;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.StringEscapeUtils;

public class DeN
{
    public static String url_en(String str)
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

    public static String url_de(String str)
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

    public static String base64_en(String str)
    {
        byte[] base64 = str.getBytes();

        String enStr = DatatypeConverter.printBase64Binary(base64);

        return enStr;
    }

    public static String base64_de(String str)
    {
        byte[] base64 = null;

        base64 = DatatypeConverter.parseBase64Binary(str);
        String deStr = new String(base64);

        return deStr;
    }

    public static String html_en(String str)
    {
        String enStr = StringEscapeUtils.escapeHtml4(str);

        return enStr;
    }

    public static String html_de(String str)
    {
        String deStr = StringEscapeUtils.unescapeHtml4(str);

        return deStr;
    }

    public static String xml_en(String str)
    {
        String enStr = StringEscapeUtils.escapeXml(str);

        return enStr;
    }

    public static String xml_de(String str)
    {
        String deStr = StringEscapeUtils.unescapeXml(str);

        return deStr;
    }

    public static String hex_en(String str)
    {
        byte[] enByte = str.getBytes();

        String enStr = Hex.encodeHexString(enByte);

        return enStr;
    }

    public static String hex_de(String str)
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

    public static String dec_en(String str)
    {
        String enStr = "";
        for (int i = 0; i < str.length(); i++)
        {
            int c = str.charAt(i);

            enStr = enStr + String.valueOf(c);
        }
        return enStr;
    }

    public static String dec_de(String str)
    {
        int dec_num = Integer.parseInt(str);

        char deChar = (char)dec_num;

        String deStr = Character.toString(deChar);

        return deStr;
    }

    public static String utf8_en(String str, int choice)
    {
        byte[] utf8bytes = StringUtils.getBytesUtf8(str);
        if (choice == 1) {
            str = utf8_url_format(utf8bytes);
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
            str = utf8_url_format(utf8_2bytes);
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
            str = utf8_url_format(utf8_3bytes);
        }
        return str;
    }

    private static String utf8_url_format(byte[] b)
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
}
