import java.io.FileNotFoundException;

public class Loader {

    private static final String IMG_DIR ="res/";
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    private static int regionCount = 101;

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        int eatForThread = (regionCount/AVAILABLE_PROCESSORS) + 1; //кол-во регионов, которые мы будем пердавать одному потоку
        int criticalPoint = (eatForThread * AVAILABLE_PROCESSORS) - eatForThread;
        int startCountForThread;
        int endCountForThread;

        for(int i = 1; i < 101; i++){
            if(i % eatForThread == 0){
                endCountForThread = i;
                startCountForThread = endCountForThread - eatForThread + 1;
                int finalI = i;
                Writer writer = new Writer(IMG_DIR + i + "thread.txt",startCountForThread,endCountForThread);
                new Thread(writer).start();
            }
            if(i == criticalPoint){
                Writer writer = new Writer(IMG_DIR + i + "thread.txt", i + 1, regionCount);
                new Thread(writer).start();
            }
        }
        Thread.sleep(10000);
        System.out.println("выполнение одним потоком: ");
        Writer writer = new Writer(IMG_DIR+"thread.txt",1,101);
        new Thread(writer).start();

    }
}
