package com.nc.model;

import java.io.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Input/Output class
 */
public class TaskIO {
    private static final SimpleDateFormat DATE_FORMAT =
            new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss.S]");
    /**
     * Writes tasks from TaskList into the binary stream
     * @param tasks tasks to write
     * @param out Output stream
     */
    public static void write(TaskList tasks, OutputStream out) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            try {
                dataOutputStream.writeInt(tasks.size());
                for (int i = 0; i < tasks.size(); i++) {
                    String taskTitle = tasks.getTask(i).getTitle();
                    int titleLength = taskTitle.length();
                    dataOutputStream.writeInt(titleLength);
                    dataOutputStream.writeUTF(taskTitle);
                    boolean isActive = tasks.getTask(i).isActive();
                    dataOutputStream.writeBoolean(isActive);
                    int interval = tasks.getTask(i).getRepeatInterval();
                    dataOutputStream.writeInt(interval);
                    if (tasks.getTask(i).isRepeated()) {
                        long startTime = tasks.getTask(i).getStartTime().getTime();
                        long endTime = tasks.getTask(i).getEndTime().getTime();
                        dataOutputStream.writeLong(startTime);
                        dataOutputStream.writeLong(endTime);
                    } else {
                        long time = tasks.getTask(i).getTime().getTime();
                        dataOutputStream.writeLong(time);
                    }
                }
            } finally {
                dataOutputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Input/Output exception");;
        }
    }

    /**
     * Reads tasks to the binary stream
     * @param tasks tasks to read
     * @param in InputStream
     */
    public static void read(TaskList tasks, InputStream in) {
        try {
            Task task = null;
            DataInputStream dataInputStream = new DataInputStream(in);
            try {
                int tasksSize = dataInputStream.readInt();
                for (int i = 0; i < tasksSize; i++) {
                    int titleLength = dataInputStream.readInt();
                    String title = dataInputStream.readUTF();
                    boolean isActive = dataInputStream.readBoolean();
                    int  interval = dataInputStream.readInt();
                    if (interval == 0) {
                        long time = dataInputStream.readLong();
                        Date date = new Date(time);
                        task = new Task(title, date);
                    } else {
                        long startTime = dataInputStream.readLong();
                        Date startDate = new Date(startTime);
                        long endTime = dataInputStream.readLong();
                        Date endDate = new Date(endTime);
                        task = new Task(title, startDate, endDate, interval);
                    }
                    task.setActive(isActive);
                    tasks.add(task);
                }
            } finally {
                dataInputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Input/Output exception");
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong parameter");
        }
    }

    /**
     * Writes tasks to the binary file
     * @param tasks tasks to write
     * @param file file
     */
    public static void writeBinary(TaskList tasks, File file) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                write(tasks, fileOutputStream);
            } finally {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            System.out.println("Input/Output exception");
        }
    }

    /**
     * Reads tasks from the binary file
     * @param tasks tasks to read
     * @param file file
     */
    public static void readBinary(TaskList tasks, File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                read(tasks, fileInputStream);
            } finally {
                fileInputStream.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find specified file");
        } catch (IOException e) {
            System.out.println("Input/Output exception");
        }
    }

    /**
     * Writes tasks in the text format
     * @param tasks tasks to write
     * @param out writer stream
     */
    public static void write(TaskList tasks, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        try {
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.getTask(i);
                writer.write("\"" + task.getTitle().replaceAll("\"", "\"\"") + "\"");
                writer.write(task.isRepeated() ? " from " : " at ");
                if (task.isRepeated()) {
                    writer.write(DATE_FORMAT.format(task.getStartTime()));
                    writer.write(" to ");
                    writer.write(DATE_FORMAT.format(task.getEndTime()));
                    writer.write(" every ");
                    writer.write("[" + task.getRepeatInterval() + "]");
                } else {
                    writer.write(DATE_FORMAT.format(task.getTime()));
                }
                writer.write(task.isActive() ? " active" : " inactive");
                writer.write(i == tasks.size() - 1 ? "." : ";");
                writer.println();
            }
        } finally {
            writer.close();
        }
    }
    
    /**
     * Reads tasks in the text format.
     * @param tasks tasks to read
     * @param in reader stream
     */
    public static void read(TaskList tasks, Reader in) {
        try {
            BufferedReader reader = new BufferedReader(in);
            try {
                String line = reader.ready() ? reader.readLine() : null;
                boolean endString = false;
                while (line != null && !endString) {
                    int lastChar = line.lastIndexOf("\"");
                    String title = line.substring(1, lastChar).replaceAll("\"\"", "\"");
                    String repeated = String.valueOf(line.charAt(lastChar + 2));

                    Task task = null;
                    int firstBracket = line.indexOf("[", lastChar);
                    int endBracket = line.lastIndexOf("]");
                    Date time = DATE_FORMAT.parse(line, new ParsePosition(firstBracket));

                    if (repeated.equals("a")) {
                        task = new Task(title, time);
                    } else {
                        int secondBracket = line.indexOf("[", firstBracket + 1);
                        Date endTime = DATE_FORMAT.parse(line, new ParsePosition(secondBracket));
                        int startInterval = line.indexOf("[", secondBracket + 1);
                        String intervalStr = line.substring(startInterval + 1, endBracket);
                        int interval = Integer.valueOf(intervalStr);
                        task = new Task(title, time, endTime, interval);
                    }

                    String inactive = line.substring(endBracket + 2, line.length() - 1);
                    task.setActive(!inactive.equals("inactive"));
                    tasks.add(task);
                    if (line.endsWith(";")) {
                        line = reader.readLine();
                    } else {
                        endString = true;
                    }
                }
            } finally {
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Input/Output exception");
        }
    }

    public static void writeText(TaskList tasks, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            try {
                write(tasks, fileWriter);
            } finally {
                fileWriter.close();
            }
        } catch (IOException e) {
            System.out.println("Input/Output exception");
        }
    }

    public static void readText(TaskList tasks, File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        read(tasks, fileReader);
        fileReader.close();
    }

}

