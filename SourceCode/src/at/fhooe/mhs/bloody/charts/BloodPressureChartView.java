/* ===========================================================
 * AFreeChart : a free chart library for Android(tm) platform.
 *              (based on AFreeChart and JCommon)
 * ===========================================================
 *
 * (C) Copyright 2010, by ICOMSYSTECH Co.,Ltd.
 * (C) Copyright 2000-2008, by Object Refinery Limited and Contributors.
 *
 * Project Info:
 *    AFreeChart: http://code.google.com/p/afreechart/
 *    JFreeChart: http://www.jfree.org/jfreechart/index.html
 *    JCommon   : http://www.jfree.org/jcommon/index.html
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * [Android is a trademark of Google Inc.]
 *
 * -----------------
 * TimeSeriesChartDemo01View.java
 * -----------------
 * (C) Copyright 2010, 2011, by ICOMSYSTECH Co.,Ltd.
 *
 * Original Author:  Niwano Masayoshi (for ICOMSYSTECH Co.,Ltd);
 * Contributor(s):   -;
 *
 * Changes
 * -------
 * 19-Nov-2010 : Version 0.0.1 (NM);
 */

package at.fhooe.mhs.bloody.charts;

import org.afree.chart.AFreeChart;
import org.afree.chart.axis.AxisLocation;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.plot.CombinedDomainXYPlot;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.StandardXYItemRenderer;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.data.xy.XYDataset;
import org.afree.data.xy.XYSeries;
import org.afree.data.xy.XYSeriesCollection;
import org.afree.graphics.geom.Font;

import android.content.Context;
import at.fhooe.mhs.bloody.R;

/**
 * TimeSeriesChartDemo01View
 */
public class BloodPressureChartView extends ChartView
{

	private Context mContext;

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public BloodPressureChartView(Context context)
	{
		super(context);
		mContext = context;

		final AFreeChart chart = createCombinedChart();
		// final AFreeChart chart = createChart(createDataset());

		setChart(chart);
	}

	private AFreeChart createCombinedChart()
	{

		// create subplot 1...
		final XYDataset bloodData = createBloodPressureDataSet();
		final XYItemRenderer bloodRenderer = new StandardXYItemRenderer();
		final NumberAxis bloodRangeAxis = new NumberAxis(mContext
				.getResources().getString(R.string.charts_sub_blood));
		final XYPlot bloodSubPlot = new XYPlot(bloodData, null, bloodRangeAxis,
				bloodRenderer);

		Font font = new Font("Axis", 0, 30);
		bloodRangeAxis.setTickLabelFont(font);

		bloodSubPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);

		// final XYTextAnnotation annotation = new XYTextAnnotation("Hello!",
		// 50.0, 10000.0);
		// annotation.setRotationAngle(Math.PI / 4.0);
		// subplot1.addAnnotation(annotation);

		// create subplot 2...
		final XYDataset airData = createAirPressureDataSet();
		final XYItemRenderer airRenderer = new StandardXYItemRenderer();
		final NumberAxis airRangeAxis = new NumberAxis(mContext.getResources()
				.getString(R.string.charts_sub_air));
		airRangeAxis.setAutoRangeIncludesZero(false);
		final XYPlot airSubplot = new XYPlot(airData, null, airRangeAxis,
				airRenderer);
		airSubplot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);

		// parent plot...
		final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(
				new DateAxis());
		plot.setGap(10.0);

		// add the subplots...
		plot.add(bloodSubPlot, 1);
		plot.add(airSubplot, 1);
		plot.setOrientation(PlotOrientation.VERTICAL);

		// return a new chart containing the overlaid plot...
		return new AFreeChart(mContext.getResources().getString(
				R.string.charts_title), AFreeChart.DEFAULT_TITLE_FONT, plot,
				true);

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return Series 1.
	 */
	private XYDataset createBloodPressureDataSet()
	{
		// create dataset 1...
		final XYSeries series1 = new XYSeries(mContext.getResources()
				.getString(R.string.systolic_value));
		series1.add(10.0, 120);
		series1.add(20.0, 123);
		series1.add(30.0, 125);
		series1.add(40.0, 122);
		series1.add(50.0, 124);
		series1.add(60.0, 128);
		series1.add(70.0, 123);
		series1.add(80.0, 118);
		series1.add(90.0, 119);
		series1.add(100.0, 118);
		series1.add(110.0, 120);
		series1.add(120.0, 121);
		series1.add(130.0, 122);
		series1.add(140.0, 122);
		series1.add(150.0, 120);

		final XYSeries series2 = new XYSeries(mContext.getResources()
				.getString(R.string.diastolic_value));
		series2.add(10.0, 15000.3);
		series2.add(20.0, 11000.4);
		series2.add(30.0, 17000.3);
		series2.add(40.0, 15000.3);
		series2.add(50.0, 14000.4);
		series2.add(60.0, 12000.3);
		series2.add(70.0, 11000.5);
		series2.add(80.0, 12000.3);
		series2.add(90.0, 13000.4);
		series2.add(100.0, 12000.6);
		series2.add(110.0, 13000.3);
		series2.add(120.0, 17000.2);
		series2.add(130.0, 18000.2);
		series2.add(140.0, 16000.2);
		series2.add(150.0, 17000.2);

		final XYSeriesCollection collection = new XYSeriesCollection();
		collection.addSeries(series1);
//		collection.addSeries(series2);
		return collection;

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return Series 2.
	 */
	private XYDataset createAirPressureDataSet()
	{

		// create dataset 2...
		final XYSeries series2 = new XYSeries(mContext.getResources()
				.getString(R.string.charts_sub_air));

		series2.add(10.0, 16853.2);
		series2.add(20.0, 19642.3);
		series2.add(30.0, 18253.5);
		series2.add(40.0, 15352.3);
		series2.add(50.0, 13532.0);
		series2.add(100.0, 12635.3);
		series2.add(110.0, 13998.2);
		series2.add(120.0, 11943.2);
		series2.add(130.0, 16943.9);
		series2.add(140.0, 17843.2);
		series2.add(150.0, 16495.3);
		series2.add(160.0, 17943.6);
		series2.add(170.0, 18500.7);
		series2.add(180.0, 19595.9);

		return new XYSeriesCollection(series2);

	}
}
