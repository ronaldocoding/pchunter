package com.example.setupbuilder.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.setupbuilder.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.view_product_activity.*

class ViewProductActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_product_activity)

        Picasso.get()
            .load("https://images4.kabum.com.br/produtos/fotos/99504/placa-mae-asus-para-amd-am4-matx-prime-b450m-gaming-br-ddr4__1545143134_gg.jpg")
            .into(product_image);
    }

    fun getUrlFromIntent(view: View) {
        val url = "https://www.kabum.com.br/produto/99504/placa-mae-asus-prime-b450m-gaming-br-amd-am4-matx-ddr4?gclid=CjwKCAiAnIT9BRAmEiwANaoE1ZC5VdC_XGLBXOfSfxibdol9RE003d2kFr7NVF2mOJg64IpHMijOvxoCXqcQAvD_BwE"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}