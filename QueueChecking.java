public class QueueChecking {
    public static void main(String[] args) {
        Primary firstThread = new Primary();
        Threads threads = new Threads(firstThread);
        Threads2 threads2 = new Threads2(firstThread);
        new Thread(threads2).start();
        new Thread(threads).start();
    }
}

class Primary {
    boolean flag = true;
    int count = 0;

    public synchronized void sent() {
        if (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Отправлено: " + count);
            flag = false;
            notify();
        }

    }

    public synchronized void received() {
        if (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Получено: " + count);
            flag = true;
            count++;

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
            firstThread.received();
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
            firstThread.sent();
        }
    }
}

