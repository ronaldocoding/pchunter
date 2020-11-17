package com.example.setupbuilder.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.R
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.view_gpu_benchmark_activity.*


class GpuBenchmarkActivity : AppCompatActivity() {

    lateinit var benchmarkChart: HorizontalBarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_gpu_benchmark_activity)
        benchmarkChart = horizontal_bar_chart
        configuresBarChart()
    }

    private fun configuresBarChart() {
        var barDataSet = getDataSet()
        barDataSet.color = Color.rgb(103, 1, 183)
        barDataSet.setDrawValues(true)

        val barData = BarData(barDataSet)
        barData.barWidth = 0.45F
        barData.setValueTextColor(Color.rgb(255, 255, 255))
        barData.setValueTextSize(16F)

        val xAxis = benchmarkChart.xAxis
        val formatter = IndexAxisValueFormatter(getXAxisValues())
        xAxis.granularity = 1F
        xAxis.valueFormatter = formatter
        xAxis.textColor = Color.rgb(255, 255, 255)
        xAxis.textSize = 16F
        xAxis.setDrawGridLines(false)
        xAxis.isEnabled = true
        xAxis.setDrawAxisLine(false)
        xAxis.labelCount = 28

        val yLeft = benchmarkChart.axisLeft
        yLeft.axisMaximum = 30000F
        yLeft.axisMinimum = 0F
        yLeft.textColor = Color.rgb(255, 255, 255)
        yLeft.isEnabled = false

        val yRight = benchmarkChart.axisRight
        yRight.setDrawAxisLine(true)
        yRight.setDrawGridLines(false)
        yRight.isEnabled = false

        benchmarkChart.setTouchEnabled(false)
        benchmarkChart.isDragEnabled = false
        benchmarkChart.setScaleEnabled(false)
        benchmarkChart.isScaleXEnabled = false
        benchmarkChart.isScaleYEnabled = false
        benchmarkChart.isClickable = false
        benchmarkChart.setDrawBarShadow(false)
        benchmarkChart.legend.isEnabled = false
        benchmarkChart.setPinchZoom(false)
        benchmarkChart.setDrawValueAboveBar(false)
        benchmarkChart.setFitBars(true)
        benchmarkChart.description.text = ""
        benchmarkChart.data = barData
        benchmarkChart.animateY(1000)
        benchmarkChart.invalidate()
    }

    private fun getDataSet(): BarDataSet {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(0f, 25029f))
        entries.add(BarEntry(1f, 23882f))
        entries.add(BarEntry(2f, 21586f))
        entries.add(BarEntry(3f, 21502f))
        entries.add(BarEntry(4f, 19374f))
        entries.add(BarEntry(5f, 18607f))
        entries.add(BarEntry(6f, 18128f))
        entries.add(BarEntry(7f, 17647f))
        entries.add(BarEntry(8f, 16711f))
        entries.add(BarEntry(9f, 16381f))
        entries.add(BarEntry(10f, 16171f))
        entries.add(BarEntry(11f, 14796f))
        entries.add(BarEntry(12f, 14456f))
        entries.add(BarEntry(13f, 14053f))
        entries.add(BarEntry(14f, 14047f))
        entries.add(BarEntry(15f, 13771f))
        entries.add(BarEntry(16f, 13467f))
        entries.add(BarEntry(17f, 13309f))
        entries.add(BarEntry(18f, 12669f))
        entries.add(BarEntry(19f, 12205f))
        entries.add(BarEntry(20f, 11553f))
        entries.add(BarEntry(21f, 11066f))
        entries.add(BarEntry(22f, 10154f))
        entries.add(BarEntry(23f, 9818f))
        entries.add(BarEntry(24f, 9482f))
        entries.add(BarEntry(25f, 9220f))
        entries.add(BarEntry(26f, 8951f))
        entries.add(BarEntry(27f, 8748f))
        return BarDataSet(entries, "Bar Data Set")
    }

    private fun getXAxisValues(): ArrayList<String> {
        val xAxis = ArrayList<String>()
        xAxis.add("RTX 3090")
        xAxis.add("RTX 3080")
        xAxis.add("RTX 2080 Ti")
        xAxis.add("RTX 3070")
        xAxis.add("RTX 2080 S")
        xAxis.add("RTX 2080")
        xAxis.add("RTX 2070 S")
        xAxis.add("GTX 1080 Ti")
        xAxis.add("RX 5700 XT")
        xAxis.add("RTX 2060 S")
        xAxis.add("RTX 2070")
        xAxis.add("GTX 1080")
        xAxis.add("RX 5700")
        xAxis.add("GTX 1070 Ti")
        xAxis.add("RTX 2060")
        xAxis.add("GTX 980 Ti")
        xAxis.add("RX 5600 XT")
        xAxis.add("GTX 1070")
        xAxis.add("GTX 1660 S")
        xAxis.add("GTX 1660 Ti")
        xAxis.add("GTX 1660")
        xAxis.add("GTX 980")
        xAxis.add("GTX 1060")
        xAxis.add("GTX 1650 S")
        xAxis.add("GTX 970")
        xAxis.add("RX 590")
        xAxis.add("GTX 780 Ti")
        xAxis.add("RX 580")
        return xAxis
    }

}