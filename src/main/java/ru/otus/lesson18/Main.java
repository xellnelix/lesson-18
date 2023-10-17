package ru.otus.lesson18;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		File file = new File(".\\files\\");
		System.out.println(Arrays.toString(file.list()));

		System.out.println("Введите название файла для работы с ним:");
		Scanner scanner = new Scanner(System.in);
		String inputFileName = scanner.nextLine();
		if (readFromFile(inputFileName)) {
			System.out.println("\nВведите строку для записи в файл:");
			String data = scanner.nextLine();
			writeToFile(inputFileName, data);
		}
	}


	public static boolean readFromFile(String fileName) {
		try (FileInputStream in = new FileInputStream("files\\" + fileName)) {
			byte[] buffer = new byte[64];
			int n = in.read(buffer);
			while (n > 0) {
				System.out.println(new String(buffer, 0, n));
				n = in.read(buffer);
			}
			return true;
		} catch (IOException e) {
			System.out.println("Не существует такого файла");
			return false;
		}
	}

	public static void writeToFile(String fileName, String data) {
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("files\\" + fileName))) {
			byte[] buffer = data.getBytes(StandardCharsets.UTF_8);
//			for (int i = 0; i < buffer.length; i++) {
			out.write(buffer);
			out.flush();
//			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}