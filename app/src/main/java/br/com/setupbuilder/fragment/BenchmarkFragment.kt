package br.com.setupbuilder.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.setupbuilder.R
import br.com.setupbuilder.view.CpuBenchmarkActivity
import br.com.setupbuilder.view.GpuBenchmarkActivity
import kotlinx.android.synthetic.main.benchmark_fragment.*

class BenchmarkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.benchmark_fragment, container, false)
    }



    override fun onStart() {
        super.onStart()

        cpu_benchmark_button.setOnClickListener {
            val intent = Intent(context, CpuBenchmarkActivity::class.java)
            startActivity(intent)
        }

        gpu_benchmark_button.setOnClickListener {
            val intent = Intent(context, GpuBenchmarkActivity::class.java)
            startActivity(intent)
        }
    }
}
