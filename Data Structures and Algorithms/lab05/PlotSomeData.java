import java.awt.*;
import java.io.IOException;

public class PlotSomeData
{
    public static void main(String[] args) throws IOException
    {
        // You can modify this to plot your gcdIterations data - sdg
        Plot.Data curve1 = Plot.data();
        Plot.Data curve2 = Plot.data();
        Plot.Data curve3 = Plot.data();
        Plot.Data curve4 = Plot.data();

        int insertCount[] = lab5.insertCount();
        int mergeCount[] = lab5.mergeCount();

        int n = 500;

        for (int x=0; x<20; x++)
        {
            curve1.xy(n,n*n); // y = x^2
            curve2.xy(n, n*(Math.log(n))); // y = x/2
            curve3.xy(n, insertCount[x]);
            curve4.xy(n, mergeCount[x]);
            n = n+500;
        }

        Plot plot = Plot.plot(Plot.plotOpts().
                title("Some Data").
                legend(Plot.LegendFormat.BOTTOM)).
                xAxis("x", Plot.axisOpts().range(500, 10000)).
                yAxis("y", Plot.axisOpts().range(0, 10000000)).

                series("y=n^2", curve1, Plot.seriesOpts().color(Color.BLACK)).
                series("y=n log n", curve2, Plot.seriesOpts().color(Color.BLUE)).
                series("y=insertion", curve3, Plot.seriesOpts().color(Color.RED)).
                series("y=merge", curve4, Plot.seriesOpts().color(Color.GREEN));

        plot.save("SomeData", "png");
    }
}
