package com.example.gpsttycmd;

import java.io.OutputStream;

/**
 * ��rootȨ��ִ��Linux�µ�Shellָ��
 *
 * @author jzj
 * @since 2014-09-09
 */
public class RootShellCmd {

    private OutputStream os;

    /**
     * ִ��shellָ��
     *
     * @param cmd
     * ָ��
     */
    public final void exec(String cmd) {
        try {
            if (os == null) {
                os = Runtime.getRuntime().exec("su").getOutputStream();
            }
            os.write((cmd+"\n").getBytes());
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��̨ģ��ȫ�ְ���
     *
     * @param keyCode
     * ��ֵ
     */
    public final void simulateKey(String keyCode) {
        exec("input keyevent " + keyCode + "\n");
    }
}
