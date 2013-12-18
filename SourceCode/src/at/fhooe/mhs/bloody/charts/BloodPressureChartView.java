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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Vector;

import org.afree.chart.AFreeChart;
import org.afree.chart.LegendItemCollection;
import org.afree.chart.axis.DateAxis;
import org.afree.chart.axis.DateTickMarkPosition;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.axis.ValueAxis;
import org.afree.chart.plot.DatasetRenderingOrder;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.XYPlot;
import org.afree.chart.renderer.xy.XYItemRenderer;
import org.afree.chart.title.TextTitle;
import org.afree.data.time.Day;
import org.afree.data.time.TimeSeries;
import org.afree.data.time.TimeSeriesCollection;
import org.afree.data.xy.IntervalXYDataset;
import org.afree.date.MonthConstants;
import org.afree.graphics.geom.Font;

import android.content.Context;
import at.fhooe.mhs.bloody.R;

/**
 * TimeSeriesChartDemo01View
 */
public class BloodPressureChartView extends ChartView
{
	private Context mContext;
	private Vector<Integer> mSystolicValues, mDiastolicValues;

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public BloodPressureChartView(Context context)
	{
		super(context);
		mContext = context;

		mSystolicValues = new Vector<Integer>();

		Integer[] sysval = new Integer[]
		{ 120, 181, 179, 159, 139, 129, 123, 118, 119, 118, 120, 121, 122, 122,
				120 };

		mSystolicValues.addAll(Arrays.asList(sysval));

		mDiastolicValues = new Vector<Integer>();

		Integer[] diaval = new Integer[]
		{ 80, 83, 85, 82, 84, 88, 83, 78, 79, 78, 80, 81, 82, 82, 80 };

		mDiastolicValues.addAll(Arrays.asList(diaval));

		final AFreeChart chart = createChart();
		// final AFreeChart chart = createChart(createDataset());

		setChart(chart);
	}

	// private AFreeChart createCombinedChart()
	// {
	//
	// // create subplot 1...
	// final XYDataset bloodData = createBloodPressureDataSet();
	// final XYItemRenderer bloodRenderer = new StandardXYItemRenderer();
	// final NumberAxis bloodRangeAxis = new NumberAxis(mContext
	// .getResources().getString(R.string.charts_sub_blood));
	// final XYPlot bloodSubPlot = new XYPlot(bloodData, null, bloodRangeAxis,
	// bloodRenderer);
	//
	// Font font = new Font("Axis", 0, 30);
	// bloodRangeAxis.setTickLabelFont(font);
	// bloodRangeAxis.setRange(0, 300);
	//
	// bloodSubPlot.setRangeAxisLocation(AxisLocation.BOTTOM_OR_LEFT);
	//
	// // final XYTextAnnotation annotation = new XYTextAnnotation("Hello!",
	// // 50.0, 10000.0);
	// // annotation.setRotationAngle(Math.PI / 4.0);
	// // subplot1.addAnnotation(annotation);
	//
	// // create subplot 2...
	// final XYDataset airData = createAirPressureDataSet();
	// final XYItemRenderer airRenderer = new StandardXYItemRenderer();
	// final NumberAxis airRangeAxis = new NumberAxis(mContext.getResources()
	// .getString(R.string.charts_sub_air));
	// airRangeAxis.setAutoRangeIncludesZero(false);
	// final XYPlot airSubplot = new XYPlot(airData, null, airRangeAxis,
	// airRenderer);
	// airSubplot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
	//
	// // parent plot...
	// final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(
	// new DateAxis());
	// plot.setGap(20.0);
	//
	// // add the subplots...
	// plot.add(bloodSubPlot, 1);
	// plot.add(airSubplot, 1);
	// plot.setOrientation(PlotOrientation.VERTICAL);
	//
	// // return a new chart containing the overlaid plot...
	// return new AFreeChart(mContext.getResources().getString(
	// R.string.charts_title), AFreeChart.DEFAULT_TITLE_FONT, plot,
	// true);
	//
	// }

	/**
	 * Creates an overlaid chart.
	 * 
	 * @return The chart.
	 */
	private AFreeChart createChart()
	{
		Font axisFont = new Font("Axis", 0, 20);
		Font titleFont = new Font("Title", 0, 30);

		DateAxis dateAxis = new DateAxis(mContext.getResources().getString(
				R.string.date_value));
		dateAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		dateAxis.setTickLabelFont(axisFont);
		dateAxis.setDateFormatOverride(new SimpleDateFormat("dd.MMM", Locale
				.getDefault()));
		dateAxis.setLabelFont(titleFont);

		ValueAxis bloodRangeAxis = new NumberAxis(mContext.getResources()
				.getString(R.string.charts_sub_blood));

		bloodRangeAxis.setTickLabelFont(axisFont);
		bloodRangeAxis.setLabelFont(titleFont);
		bloodRangeAxis.setRange(0, 200);

		IntervalXYDataset barDiaData = createBarDataSet();

		mContext.getResources().getColor(R.color.normal_green);

		XYItemRenderer barRenderer = new CustomBarRenderer(mContext, 2, false,
				mSystolicValues, mDiastolicValues);

		// barRenderer.setSeriesPaintType(0, new SolidColor(mContext
		// .getResources().getColor(R.color.normal_green)));
		// barRenderer.setSeriesPaintType(1, new SolidColor(mContext
		// .getResources().getColor(R.color.light_green)));
		// barRenderer.setSeriesPaintType(2, new SolidColor(mContext
		// .getResources().getColor(R.color.light_blue)));
		// barRenderer.setSeriesPaintType(3, new SolidColor(mContext
		// .getResources().getColor(R.color.light_orange)));
		// barRenderer.setSeriesPaintType(4, new SolidColor(mContext
		// .getResources().getColor(R.color.normal_orange)));
		// barRenderer.setSeriesPaintType(5, new SolidColor(mContext
		// .getResources().getColor(R.color.normal_red)));

		XYPlot plot = new XYPlot(barDiaData, dateAxis, bloodRangeAxis,
				barRenderer);

		IntervalXYDataset barSysData = createBarDataSet();

		plot.setDataset(2, barSysData);
		plot.setRenderer(2, barRenderer);

		// PaintType paintType = new SolidColor(Color.GREEN);
		//
		// plot.getRendererForDataset(plot.getDataset(0)).setSeriesPaintType(0,
		// paintType);

		// create subplot 2...
		// XYDataset data2A = createDataset2A();
		// plot.setDataset(1, data2A);
		// XYItemRenderer renderer2A = new StandardXYItemRenderer();
		// plot.setRenderer(1, renderer2A);
		// renderer2A.setSeriesStroke(0, 2.0f);
		//
		// XYDataset data2B = createDataset2B();
		// plot.setDataset(2, data2B);
		// plot.setRenderer(2, new StandardXYItemRenderer());
		// plot.getRenderer(2).setSeriesStroke(0, 2.0f);

		// plot.mapDatasetToRangeAxis(2, 1);

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
		plot.setOrientation(PlotOrientation.VERTICAL);

		// LegendItemCollection legendItemsOld = plot.getLegendItems();
		final LegendItemCollection legendItemsNew = new LegendItemCollection();

		// for (int i = 0; i < 6; i++)
		// {
		// LegendItem item = legendItemsOld.get(i);
		// item.setLabelFont(axisFont);
		// legendItemsNew.add(item);
		// }

		plot.setFixedLegendItems(legendItemsNew);

		AFreeChart chart = new AFreeChart(mContext.getResources().getString(
				R.string.timeline), AFreeChart.DEFAULT_TITLE_FONT, plot, true);

		TextTitle title = chart.getTitle();
		title.setFont(titleFont);
		chart.setTitle(title);

		return chart;
	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return Series 1.
	 */
	private IntervalXYDataset createBarDataSet()
	{
		// TimeSeries optimal = new
		// TimeSeries(mContext.getResources().getString(
		// R.string.charts_optimal_value));
		// TimeSeries normal = new TimeSeries(mContext.getResources().getString(
		// R.string.charts_normal_value));
		// TimeSeries highNormal = new TimeSeries(mContext.getResources()
		// .getString(R.string.charts_normal_high_value));
		// TimeSeries hypertony1 = new TimeSeries(mContext.getResources()
		// .getString(R.string.charts_hypertonia1_value));
		// TimeSeries hypertony2 = new TimeSeries(mContext.getResources()
		// .getString(R.string.charts_hypertonia2_value));
		// TimeSeries hypertony3 = new TimeSeries(mContext.getResources()
		// .getString(R.string.charts_hypertonia3_value));
		//
		// for (int i = 0; i < values.size(); i++)
		// {
		// int value = values.get(i);
		//
		// if (value < scala[0])
		// optimal.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
		// values.get(i));
		// else if (value < scala[1])
		// normal.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
		// values.get(i));
		// else if (value < scala[2])
		// highNormal.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
		// values.get(i));
		// else if (value < scala[3])
		// hypertony1.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
		// values.get(i));
		// else if (value < scala[4])
		// hypertony2.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
		// values.get(i));
		// else
		// hypertony3.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
		// values.get(i));
		// }

		TimeSeries systolic = new TimeSeries(mContext.getResources().getString(
				R.string.systolic_value));

		TimeSeries diastolic = new TimeSeries(mContext.getResources()
				.getString(R.string.diastolic_value));

		for (int i = 0; i < mSystolicValues.size(); i++)
		{
			systolic.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
					mSystolicValues.get(i));
		}

		for (int i = 0; i < mDiastolicValues.size(); i++)
		{
			diastolic.add(new Day(i + 1, MonthConstants.DECEMBER, 2013),
					mDiastolicValues.get(i));
		}

		TimeSeriesCollection collection = new TimeSeriesCollection();

		collection.addSeries(diastolic);
		collection.addSeries(systolic);

		return collection;
	}

	// /**
	// * Creates a sample dataset.
	// *
	// * @return Series 2.
	// */
	// private XYDataset createAirPressureDataSet()
	// {
	//
	// // create dataset 2...
	// final XYSeries series2 = new XYSeries(mContext.getResources()
	// .getString(R.string.charts_sub_air));
	//
	// series2.add(10.0, 16853.2);
	// series2.add(20.0, 19642.3);
	// series2.add(30.0, 18253.5);
	// series2.add(40.0, 15352.3);
	// series2.add(50.0, 13532.0);
	// series2.add(100.0, 12635.3);
	// series2.add(110.0, 13998.2);
	// series2.add(120.0, 11943.2);
	// series2.add(130.0, 16943.9);
	// series2.add(140.0, 17843.2);
	// series2.add(150.0, 16495.3);
	// series2.add(160.0, 17943.6);
	// series2.add(170.0, 18500.7);
	// series2.add(180.0, 19595.9);
	//
	// return new XYSeriesCollection(series2);
	//
	// }
}
