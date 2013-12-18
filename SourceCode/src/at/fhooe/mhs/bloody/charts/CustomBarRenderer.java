package at.fhooe.mhs.bloody.charts;

import java.util.Vector;

import org.afree.chart.renderer.xy.ClusteredXYBarRenderer;
import org.afree.graphics.PaintType;
import org.afree.graphics.SolidColor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import at.fhooe.mhs.bloody.R;

public class CustomBarRenderer extends ClusteredXYBarRenderer
{
	private static final long serialVersionUID = 1L;

	private static final int[] SYS_SCALA = new int[]
	{ 120, 130, 140, 160, 180, 300 };

	private static final int[] DIA_SCALA = new int[]
	{ 80, 85, 90, 100, 110, 200 };

	private Vector<PaintType> mColors;
	private Vector<Integer> mSysValues, mDiaValues;

	/**
	 * Creates a new renderer.
	 * 
	 * @param colors
	 *            the colors.
	 */
	public CustomBarRenderer(Context context, Vector<Integer> sysValues,
			Vector<Integer> diaValues)
	{
		super();

		mSysValues = sysValues;
		mDiaValues = diaValues;

		mColors = new Vector<PaintType>();
		addPaintTypes(context);
	}

	public CustomBarRenderer(Context context, int margin, boolean centerBar,
			Vector<Integer> sysValues, Vector<Integer> diaValues)
	{
		super(margin, centerBar);

		mSysValues = sysValues;
		mDiaValues = diaValues;

		mColors = new Vector<PaintType>();
		addPaintTypes(context);
	}

	private void addPaintTypes(Context context)
	{
		mColors.add(new SolidColor(context.getResources().getColor(
				R.color.normal_green)));
		mColors.add(new SolidColor(context.getResources().getColor(
				R.color.light_green)));
		mColors.add(new SolidColor(context.getResources().getColor(
				R.color.light_blue)));
		mColors.add(new SolidColor(context.getResources().getColor(
				R.color.light_orange)));
		mColors.add(new SolidColor(context.getResources().getColor(
				R.color.normal_orange)));
		mColors.add(new SolidColor(context.getResources().getColor(
				R.color.normal_red)));
		mColors.add(new SolidColor(Color.GRAY));
	}

	/**
	 * Returns the paint for an item. Overrides the default behaviour inherited
	 * from AbstractSeriesRenderer.
	 * 
	 * @param row
	 *            the series.
	 * @param column
	 *            the category.
	 * 
	 * @return The item color.
	 */
	@Override
	public PaintType getItemPaintType(final int row, final int column)
	{
		int[] scala = null;
		int value = 0;

		if (row == 1)
		{
			scala = SYS_SCALA;
			value = mSysValues.get(column);
		}
		else
		{
			scala = DIA_SCALA;
			value = mDiaValues.get(column);
		}

		return getPaintTypeByScala(value, scala);
	}

	private PaintType getPaintTypeByScala(int value, int[] scala)
	{
		for (int i = 0; i < scala.length; i++)
		{
			if (value < scala[i])
				return mColors.get(i);
		}

		return mColors.get(0);
	}

}
