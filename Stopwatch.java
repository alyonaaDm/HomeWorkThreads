class StopWatch extends Thread {

    @Override
    public void run() {
        final int time = 5000;
        int runTime = 0;
        if (!Thread.interrupted()) {
            for (int checkPoint = 0; checkPoint < 5; checkPoint++) {
                final int minTime = time / 50;
                final int maxTime = time / 3;
                int genTime = (int) (Math.random() * (maxTime - minTime + 1)) + minTime;
                runTime += genTime;
                try {
                    Thread.sleep(genTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Итого прошло:" + " " + runTime + " " + "мс");
        }
    }
}

class MainThread {
    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (stopWatch.isAlive()) {
            stopWatch.interrupt();
            System.out.println("Прошло более 5 секунд");
        }
    }
}