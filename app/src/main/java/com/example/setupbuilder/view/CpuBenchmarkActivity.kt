package com.example.setupbuilder.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.ColorSpace
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.setupbuilder.R
import com.example.setupbuilder.util.RoundedBarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.view_cpu_benchmark_activity.*


class CpuBenchmarkActivity : AppCompatActivity() {

    lateinit var benchmarkChart: HorizontalBarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_cpu_benchmark_activity)
        benchmarkChart = horizontal_bar_chart
        configuresBarChart()
    }

    private fun configuresBarChart() {
        var barDataSet = getDataSet()
        setBarChartColor(barDataSet)
        barDataSet.setDrawValues(true)
        val barData = BarData(barDataSet)
        barData.barWidth = 0.7F
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
        yLeft.axisMaximum = 35000F
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
        benchmarkChart.renderer =
            RoundedBarChart(benchmarkChart, benchmarkChart.animator, benchmarkChart.viewPortHandler)
        benchmarkChart.animateY(1000)
        benchmarkChart.invalidate()
    }

    private fun getDataSet(): BarDataSet {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(27f, 8983f))
        entries.add(BarEntry(26f, 9408f))
        entries.add(BarEntry(25f, 9596f))
        entries.add(BarEntry(24f, 10872f))
        entries.add(BarEntry(23f, 11760f))
        entries.add(BarEntry(22f, 12421f))
        entries.add(BarEntry(21f, 12708f))
        entries.add(BarEntry(20f, 12779f))
        entries.add(BarEntry(19f, 12786f))
        entries.add(BarEntry(18f, 13085f))
        entries.add(BarEntry(17f, 13221f))
        entries.add(BarEntry(16f, 13382f))
        entries.add(BarEntry(15f, 14075f))
        entries.add(BarEntry(14f, 14619f))
        entries.add(BarEntry(13f, 14640f))
        entries.add(BarEntry(12f, 17587f))
        entries.add(BarEntry(11f, 17859f))
        entries.add(BarEntry(10f, 18334f))
        entries.add(BarEntry(9f, 18880f))
        entries.add(BarEntry(8f, 19693f))
        entries.add(BarEntry(7f, 21164f))
        entries.add(BarEntry(6f, 21908f))
        entries.add(BarEntry(5f, 22237f))
        entries.add(BarEntry(4f, 22683f))
        entries.add(BarEntry(3f, 22822f))
        entries.add(BarEntry(2f, 23350f))
        entries.add(BarEntry(1f, 24272f))
        entries.add(BarEntry(0f, 30863f))
        return BarDataSet(entries, "Bar Data Set")
    }

    private fun getXAxisValues(): ArrayList<String> {
        val xAxis = ArrayList<String>()
        xAxis.add("Ryzen 3900")
        xAxis.add("i9-10900K")
        xAxis.add("Ryzen 3800X")
        xAxis.add("Ryzen 3700X")
        xAxis.add("i9-10900X")
        xAxis.add("Ryzen 5600X")
        xAxis.add("i9-9900X")
        xAxis.add("Ryzen 4700G")
        xAxis.add("i7-10700K")
        xAxis.add("i9-9900K")
        xAxis.add("Ryzen 3600X")
        xAxis.add("Ryzen 3600")
        xAxis.add("Ryzen 2700X")
        xAxis.add("i5-10600K")
        xAxis.add("i7-9700K")
        xAxis.add("Ryzen 2600X")
        xAxis.add("Ryzen 3500X")
        xAxis.add("Ryzen 2600")
        xAxis.add("Ryzen 1600X")
        xAxis.add("Ryzen 3300X")
        xAxis.add("Ryzen 3500")
        xAxis.add("i5-10400F")
        xAxis.add("Ryzen 1600")
        xAxis.add("Ryzen 3100")
        xAxis.add("i5-9600K")
        xAxis.add("i5-9400F")
        xAxis.add("Ryzen 3400G")
        xAxis.add("i3-10100")
        return xAxis
    }

    private fun setBarChartColor(barData: BarDataSet) {
        barData.setColors(
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange),
            ContextCompat.getColor(benchmarkChart.context, R.color.intelBlue),
            ContextCompat.getColor(benchmarkChart.context, R.color.amdOrange)
        )
    }
}