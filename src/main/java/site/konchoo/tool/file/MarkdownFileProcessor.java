package site.konchoo.tool.file;

import javafx.scene.control.TextArea;
import org.apache.commons.lang3.StringUtils;
import site.konchoo.tool.constants.AppConst;
import site.konchoo.tool.parameter.ComponentParameter;
import site.konchoo.tool.utils.ListUtils;
import site.konchoo.tool.utils.LogsUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class MarkdownFileProcessor {
    private MarkdownFileProcessor() {
    }

    public static void exportMarkdownFile(TextArea logInfoTextArea, List<File> markdownFiles, ComponentParameter componentParameter) {
        markdownFiles.forEach(markdownFile -> {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(markdownFile), StandardCharsets.UTF_8));
                List<String> markdownTextLineList = new ArrayList<>();
                String line;
                logInfoTextArea.appendText(LogsUtils.printLog("loading markdown file , path : " + markdownFile.getAbsolutePath()));
                while ((line = bufferedReader.readLine()) != null) {
                    if (StringUtils.isBlank(line)) continue;
                    markdownTextLineList.add(line);
                }
                logInfoTextArea.appendText(LogsUtils.printLog("loading markdown file done."));
                logInfoTextArea.appendText(LogsUtils.printLog("processing line text..."));
                List<String> newMarkdownTextLineList = ListUtils.unionLists(
                        generateMarkdownHeaderInfo(markdownFile, componentParameter),
                        removePreviousMarkdownHeaderInfo(markdownTextLineList));
                logInfoTextArea.appendText(LogsUtils.printLog("processing line text done."));
                logInfoTextArea.appendText(LogsUtils.printLog("output markdown file..."));
                // output markdown file
                Files.write(markdownFile.toPath(), newMarkdownTextLineList, StandardCharsets.UTF_8);
                logInfoTextArea.appendText(LogsUtils.printLog("output markdown file done , path : " + markdownFile.getAbsolutePath()));
            } catch (IOException e) {
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                LogsUtils.printLog(stringWriter.toString());
            }
        });
    }

    private static List<String> removePreviousMarkdownHeaderInfo(List<String> textLineList) {
        if (!textLineList.isEmpty() && AppConst.INFO_BODY.equals(textLineList.get(0))) {
            final int bounds = textLineList.subList(1, textLineList.size()).indexOf(AppConst.INFO_BODY) + 1;
            return textLineList.subList(bounds + 1, textLineList.size());
        } else {
            return textLineList;
        }
    }

    private static List<String> generateMarkdownHeaderInfo(File markdownFile, ComponentParameter componentParameter) {
        //title base on file name
        String title = markdownFile.getName().substring(0, markdownFile.getName().lastIndexOf('.'));
        componentParameter.setTitle(title);
        List<String> infoList = new ArrayList<>();
        infoList.add(AppConst.INFO_BODY);
        final Field[] fields = componentParameter.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                final Object value = field.get(componentParameter);
                if (value instanceof String) {
                    if (StringUtils.isNotEmpty((String) value)) infoList.add(field.getName() + ": " + value);
                } else if (value instanceof Boolean && (boolean) value) {
                    BasicFileAttributes attrs = Files.readAttributes(markdownFile.toPath(), BasicFileAttributes.class);
                    String createdTime = AppConst.sdf.format(new Date(attrs.creationTime().toMillis()));
                    String lastModifyTime = AppConst.sdf.format(new Date(attrs.lastModifiedTime().toMillis()));
                    if ("isSelectedFileCreatedTime".equals(field.getName())) {
                        infoList.add("date: " + createdTime);
                    } else infoList.add("date: " + lastModifyTime);
                }
            } catch (IllegalAccessException | IOException e) {
                StringWriter stringWriter = new StringWriter();
                e.printStackTrace(new PrintWriter(stringWriter));
                LogsUtils.printLog(stringWriter.toString());
            }
        }
        infoList.add(AppConst.INFO_BODY);
        return infoList;
    }
}
