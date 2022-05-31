import java.awt.*;
import java.io.IOException;

public class PlotSomeData
{
    public static void main(String[] args) throws IOException
    {
        // You can modify this to plot your gcdIterations data - sdg
        Plot.Data curve1 = Plot.data();
        Plot.Data curve2 = Plot.data();

        for (int x=0; x<100; x++)
        {
            curve1.xy(x,1); // y = x^2
            curve2.xy(x, 2*(Math.log(2)));     // y = x/2
        }

        Plot plot = Plot.plot(Plot.plotOpts().
                title("Some Data").
                legend(Plot.LegendFormat.BOTTOM)).
                xAxis("x", Plot.axisOpts().range(0, 100)).
                yAxis("y", Plot.axisOpts().range(0, 100)).

                series("y=x^2", curve1, Plot.seriesOpts().color(Color.BLACK)).
                series("y=x/2", curve2, Plot.seriesOpts().color(Color.BLUE));

        plot.save("SomeData", "png");
    }
}
