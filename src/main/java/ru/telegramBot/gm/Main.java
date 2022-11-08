package ru.telegramBot.gm;


import java.util.Scanner;

public class Main {
    /**
     * Главный метод запуска бота, который предоставляет выбор, какого бота запускать
     *
     * @param args Не используются
     */
    public static void main(String[] args) {
        int result = chooseBot();
        switch (result) {
            case 0:
                break;
            case 1:
                ru.telegramBot.gm.clients.telegramBot.Main.main(new String[]{});
                break;
            case 2:
                ru.telegramBot.gm.clients.consoleBot.Main.main(new String[]{});
        }
    }

    /**
     * Метод, который получает у пользователя информацию о том, какого бота необходимо запустить
     *
     * @return <ul><li>0 - exit</li><li>1 - Telegram bot</li><li>2 - Console bot</li></ul>
     */
    private static int chooseBot() {
        System.out.print("Choose which bot you want to run (t/c/e/?): ");
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.next();
        return switch (answer) {
            case "e" -> {
                System.out.println("Bot start canceled");
                yield 0;
            }
            case "t" -> {
                System.out.println("Starting a telegram bot...");
                yield 1;
            }
            case "c" -> {
                System.out.println("Starting a console bot...");
                yield 2;
            }
            default -> {
                System.out.println("t - Starting a telegram bot. The connection data is obtained from the config file");
                System.out.println("c - Starting a console bot");
                System.out.println("e - (exit) cancel the start of the bot");
                System.out.println("? - getting help on launching bots");
                yield chooseBot();
            }
        };
    }
}
