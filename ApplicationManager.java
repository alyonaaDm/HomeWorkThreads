public class ApplicationManager {
    public static void main(String[] args) {
        Runnable myRunnable = () -> {
            int time = (int) (Math.random() * 901) + 100;
            int k = (int) (Math.random() * 6) + 5;
            for (int i = 0; i < k; i++) {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread primary = new Thread(myRunnable);
        primary.setName("primary");
        Thread secondary = new Thread(myRunnable);
        secondary.setName("secondary");
        Thread ternary = new Thread(myRunnable);
        ternary.setName("ternary");
        primary.start();
        try {
            primary.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        secondary.start();
        try {
            secondary.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ternary.start();
        try {
            ternary.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

