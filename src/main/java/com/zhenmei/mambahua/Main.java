package com.zhenmei.mambahua;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.thread.ExecutorBuilder;
import cn.hutool.core.thread.GlobalThreadPool;
import cn.hutool.http.HttpRequest;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.http.client").setLevel(Level.OFF);

        int captureCount = 35;

        for (int i = 1; i <= captureCount; i++) {
            if (i >= 10) {
                DownloadThread downloadThread = new DownloadThread("27557", String.format("%02d", i));
                ThreadPool.getThreadPool().executor(downloadThread);
            } else {
                DownloadThread downloadThread = new DownloadThread("27557", String.format("%03d", i));
                ThreadPool.getThreadPool().executor(downloadThread);
            }

        }


    }
}
