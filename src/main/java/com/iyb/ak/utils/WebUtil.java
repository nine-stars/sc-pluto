package com.iyb.ak.utils;

import com.iyb.ak.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class WebUtil {

    static public void sendJsonError(HttpServletRequest request, HttpServletResponse response, BaseException e, HttpStatus status) {
        ServletOutputStream out = null;
        try {
            response.setStatus(status.value());
            response.setContentType("application/json;charset=UTF-8");
            String msg = e.getJsonMessage();
            byte[] msgbyte = msg.getBytes("UTF-8");
            response.setContentLength(msgbyte.length);
            out = response.getOutputStream();
            out.write(msgbyte);
            out.close();
        } catch (IOException e1) {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
        }
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        if (remoteAddr == null || remoteAddr.trim().length() <= 0) {
            remoteAddr = "unknown";
        }

        return remoteAddr.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : remoteAddr;
    }
}
