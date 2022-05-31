//Adam Youssef
//Student number: 104785050

import java.awt.*;
import java.io.IOException;

public class lab02 {

    public static int bruteForce(int[] a){
        int maxSum = 0;
        int sum;
        int n = a.length;
        int start = -1;
        int end = -1;
        int iterations = 0;

        for(int i=1; i<n; i++){
            sum = 0;
            for(int j=i; j<n; j++){
                sum+=a[j];
                if(sum > maxSum){
                    maxSum = sum;
                    start = i;
                    end = j;
                }
                iterations++;
            }
        }

        return iterations;
    }

    public static int improvedMCS(int a[]){
        int i = 1;
        int start = -1;
        int end = -1;
        int maxSum = 0;
        int sum = 0;
        int n = a.length;
        int iterations = 0;

        for(int j = 1; j<n; j++){
           sum += a[j];
           if(sum > maxSum){
               maxSum = sum;
               start = i;
               end = j;
           }
           if(sum < 0){
               i = j+1;
               sum = 0;
           }
           iterations++;
        }

        System.out.println("Iterations: " + iterations);

        return iterations;
    }



    public static void main(String[]args) throws IOException {
        int bruteForceIterations[] = new int[11];
        int improvedIterations[] = new int[11];
        int min = -10;
        int max = 10;

        for(int i = 5;i<=15;i++){
            for(int j = 0;j<10;j++){
                int[] a = new int[i];
                for(int k = 0;k<i;k++){
                    a[k]=(int)Math.floor(Math.random()*(max-min+1)+min);
                }
                if(improvedIterations[i-5]< improvedMCS(a)){
                    improvedIterations[i-5]= improvedMCS(a);
                }
                if(bruteForceIterations[i-5]< bruteForce(a)){
                    bruteForceIterations[i-5] = bruteForce(a);
                }
            }
        }

        Plot.Data curve1 = Plot.data();
        Plot.Data curve2 = Plot.data();

        for (int x=0; x<=10; x++)
        {
            //for the brute force graph
            curve1.xy(x, bruteForceIterations[x]);
            curve2.xy(x, 1+x*(x+1)/2);

            /*
             I ran these lines to get the improved MCS graph:
             curve1.xy(x, improvedIterations[x]);
             curve2.xy(x, x);
             */
        }

        Plot plot = Plot.plot(Plot.plotOpts().
                title("Some Data").
                legend(Plot.LegendFormat.BOTTOM)).
                xAxis("x", Plot.axisOpts().range(0, 11)).
                yAxis("y", Plot.axisOpts().range(0, 100)).

                series("y=x^2", curve1, Plot.seriesOpts().color(Color.BLACK)).
                series("y=x/2", curve2, Plot.seriesOpts().color(Color.BLUE));

        plot.save("SomeData", "png");
    }
}