public class TikTak {
    public static void main(String[] args) {
        Primary firstThread = new Primary();
        Threads threads = new Threads(firstThread);
        Threads2 threads2 = new Threads2(firstThread);
        new Thread(threads).start();
        new Thread(threads2).start();
    }
}

class Primary {
    boolean flag = true;

    public synchronized void tik() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Tik");
            flag = false;
            notify();
        }

    }

    public synchronized void tak() {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Tak");
            flag = true;
            notify();
        }

    }
}

class Threads implements Runnable {
    Primary firstThread;

    public Threads(Primary firstThread) {
        this.firstThread = firstThread;
    }

    public void run() {
        while (true) {
            firstThread.tak();
        }
    }
}

class Threads2 implements Runnable {
    Primary firstThread;

    public Threads2(Primary firstThread) {
        this.firstThread = firstThread;
    }

    public void run() {
        while (true) {
            firstThread.tik();
        }
    }
}
