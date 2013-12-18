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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.afree.graphics.geom.Font;

import android.content.Context;
import at.fhooe.mhs.bloody.R;
import at.fhooe.mhs.bloody.measurementdata.Measurement;
import at.fhooe.mhs.bloody.measurementdata.MeasurementModel;

/**
 * TimeSeriesChartDemo01View
 */
public class BloodPressureChartView extends ChartView
{
	private Context mContext;
	private Vector<Integer> mSystolicValues, mDiastolicValues;
	private Vector<Date> mDates;

	/**
	 * constructor
	 * 
	 * @param context
	 */
	public BloodPressureChartView(Context context)
	{
		super(context);
		mContext = context;

		MeasurementModel model = MeasurementModel.getInstance(context);

		ArrayList<Measurement> measurements = model.getMeasurements();

		mSystolicValues = new Vector<Integer>();
		mDiastolicValues = new Vector<Integer>();
		mDates = new Vector<Date>();
		Date date = null;

		for (Measurement measurement : measurements)
		{
			date = getDateFromString(measurement.getDate());

			if (date != null)
			{
				mDates.add(date);
				mSystolicValues.add(measurement.getSystolic());
				mDiastolicValues.add(measurement.getDiastolic());
			}
		}

		// mSystolicValues = new Vector<Integer>();
		//
		// Integer[] sysval = new Integer[]
		// { 120, 181, 179, 159, 139, 129, 123, 118, 119, 118, 120, 121, 122,
		// 122,
		// 120 };
		//
		// mSystolicValues.addAll(Arrays.asList(sysval));
		//
		// mDiastolicValues = new Vector<Integer>();
		//
		// Integer[] diaval = new Integer[]
		// { 80, 83, 85, 82, 84, 88, 83, 78, 79, 78, 80, 81, 82, 82, 80 };
		//
		// mDiastolicValues.addAll(Arrays.asList(diaval));

		final AFreeChart chart = createChart();
		// final AFreeChart chart = createChart(createDataset());

		setChart(chart);
	}

	private Date getDateFromString(String dateString)
	{
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy",
				Locale.getDefault());
		try
		{
			return format.parse(dateString);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

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
				.getString(R.string.diastolic_value)
				+ " / "
				+ mContext.getResources().getString(R.string.systolic_value));

		bloodRangeAxis.setTickLabelFont(axisFont);
		bloodRangeAxis.setLabelFont(titleFont);
		bloodRangeAxis.setRange(0, 200);

		IntervalXYDataset barDiaData = createBarDataSet();

		mContext.getResources().getColor(R.color.normal_green);

		XYItemRenderer barRenderer = new CustomBarRenderer(mContext, 2, false,
				mSystolicValues, mDiastolicValues);

		XYPlot plot = new XYPlot(barDiaData, dateAxis, bloodRangeAxis,
				barRenderer);

		IntervalXYDataset barSysData = createBarDataSet();

		plot.setDataset(2, barSysData);
		plot.setRenderer(2, barRenderer);

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
		TimeSeries systolic = new TimeSeries(mContext.getResources().getString(
				R.string.systolic_value));

		TimeSeries diastolic = new TimeSeries(mContext.getResources()
				.getString(R.string.diastolic_value));

		Date date = null;

		for (int i = 0; i < mSystolicValues.size(); i++)
		{
			date = mDates.get(i);

			systolic.add(
					new Day(date.getDay(), date.getMonth(), date.getYear()),
					mSystolicValues.get(i));
		}

		for (int i = 0; i < mDiastolicValues.size(); i++)
		{
			date = mDates.get(i);

			diastolic.add(
					new Day(date.getDay(), date.getMonth(), date.getYear()),
					mDiastolicValues.get(i));
		}

		TimeSeriesCollection collection = new TimeSeriesCollection();

		collection.addSeries(diastolic);
		collection.addSeries(systolic);

		return collection;
	}
}
