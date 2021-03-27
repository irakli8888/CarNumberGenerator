import java.io.IOException;
import java.io.PrintWriter;

public class Writer implements Runnable {

    private String filePath;
    private int startRegionCount;
    private int endRegionCount;
    private String regions[];
    private String carNumbers[];

    public Writer(String filePath, int startRegionCount, int endRegionCount){
        this.startRegionCount = startRegionCount;
        this.endRegionCount = endRegionCount;
        this.filePath = filePath;
        this.regions = getMass(2,101);
        this.carNumbers = getMass(3,1000);
    }

    public static String[] getMass(int numberLength, int masLength){
        String mass[] = new String[masLength];
        for(int i = 1; i < masLength; i++){
            int padSize = numberLength - String.valueOf(i).length();
            StringBuilder sb = new StringBuilder();
            for(int x = 0; x < padSize; x++){
                sb.append("0");
            }
            mass[i] = sb.append(i).toString();
        }
        return mass;
    }

    @Override
    public void run() {

        long start = System.currentTimeMillis();

        try (PrintWriter writer = new PrintWriter(filePath)) {

            char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
            for(int regionCode = startRegionCount; regionCode < endRegionCount; regionCode++) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int number = 1; number < 100; number++) {
                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                stringBuilder.append(firstLetter);
                                stringBuilder.append(carNumbers[number]);
                                stringBuilder.append(secondLetter);
                                stringBuilder.append(thirdLetter);
                                stringBuilder.append(regions[regionCode]);
                                stringBuilder.append("\n");
                            }
                        }
                    }
                }
                writer.write(stringBuilder.toString());
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "; время выполнения -" + (System.currentTimeMillis() - start) + " ms");
    }
}
