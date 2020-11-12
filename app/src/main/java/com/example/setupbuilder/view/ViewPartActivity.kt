package com.example.setupbuilder.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.setupbuilder.R
import com.example.setupbuilder.adapters.TipsRecyclerAdapter
import kotlinx.android.synthetic.main.view_part_activity.*

class ViewPartActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<TipsRecyclerAdapter.ViewHolder>? = null

    private val names = arrayOf(
        "Processador (CPU)",
        "Placa-mãe (Motherboard)",
        "Memória de Acesso Aleatório (RAM)",
        "Placa de vídeo(GPU)",
        "Disco de Armazenamento (Storage)",
        "Monitor",
        "Teclado",
        "Mouse",
        "Headset",
        "Fonte de Alimentação (PSU)",
        "Gabinete (Case)"
    )

    private val key = arrayOf(
        "CPU",
        "placa-mãe",
        "Memória RAM",
        "Placa de vídeo",
        "hd externo",
        "Monitor",
        "Teclado",
        "Mouse",
        "Headset",
        "Fonte de alimentação",
        "Gabinete"
    )

    private val definitions = arrayOf(
        "A unidade central de processamento ou CPU (Central Processing Unit), " +
                "também conhecida como processador, é a parte de um sistema computacional, " +
                "que realiza as instruções de um programa de computador, para executar a" +
                "aritmética básica, lógica, e a entrada e saída de dados.",
        "É a parte do computador responsável por conectar e interligar todos os componentes." +
                "Além de permitir o tráfego de informação, a placa também alimenta alguns" +
                " periféricos com a energia elétrica que recebe da fonte de alimentação.",
        "A Memória de acesso randômico ou Memória de acesso aleatório (Random Access Memory, frequentemente " +
                "abreviado para RAM) é um tipo de memória que permite a leitura e a escrita, utilizada como" +
                " memória primária em sistemas eletrônicos digitais.",
        "É o componente do hardware do computador responsável por administrar e controlar as funções " +
                "de exibição de vídeo na tela. Todo computador contemporâneo usa uma interface gráfica",
        "É um dispositivo capaz de armazenar informações (dados) para posterior consulta ou uso. ",
        "Unidade utilizada para exibir visualmente texto ou gráficos gerados por computador.",
        "É um tipo de periférico de entrada utilizado pelo usuário para a entrada manual no sistema de dados e comandos.",
        "Dispositivo que se liga ao computador e que serve para transmitir ordens por botões e alterar" +
                " a posição do cursor sobre o monitor do computador.",
        "É um conjunto de fone de ouvido com controle de volume e microfone",
        "Fonte de alimentação ou PSU (Power Supply Unit) é um dispositivo que fornece energia elétrica para um computador.",
        "É o compartimento que contém a maioria dos componentes de um computador."

    )

    private val photos = arrayOf(
        R.drawable.processador,
        R.drawable.placa_mae,
        R.drawable.ram,
        R.drawable.placa_de_video,
        R.drawable.storage,
        R.drawable.monitor,
        R.drawable.teclado,
        R.drawable.mouse,
        R.drawable.headseat,
        R.drawable.fonte,
        R.drawable.gabinete
    )

    private val tips = arrayOf(
        arrayOf(
            "Dependendo do seu tipo de uso para o computador, é preferível mais ou menos núcleos e threads (conjunto de tarefas existentes em um ou mais programas, executadas ao mesmo tempo pelo processador)",
            "Para uso doméstico, que seja responder e enviar e-mails, acessar à Internet e jogar games não tão exigentes, um processador de 4 núcleos e 4 threads (conhecido como quad-core) já é o suficiente",
            "Para jogar games mais exigentes já é necessário um processador quad-core, com hyper threading (o dobro de threads convencionais) e em altas frequências. Exemplo: AMD Ryzen 3 3300x com 4 núcleos e 8 threads",
            "Para tarefas profissionais (edição de vídeo, modelagem 3D, etc) e jogos ainda mais exigentes é necessário um processador six-core, também com hyper threading e em médias ou altas frequências. Exemplos: Intel I5 9600K, com 6 núcleos e 12 threads, e  AMD Ryzen 5 3600, também com 6 núcleos e 12 threads.",
            "Fique atento se o socket do processador que pretende comprar é compatível com sua placa-mãe. Exemplos de socket: AM4, para AMD, e LG 1151, para Intel",
            "Verifique se o processador que pretende comprar tem placa de vídeo integrada, se não houver, será necessário comprar um placa de vídeo dedicada",
            "Alguns processador já acompanham na caixa um cooler, outros não, fique atento a isso."
        ),
        arrayOf(
            "Dê preferência a placas-mãe com no mínimo 2 compartimentos para memória RAM;",
            "Verifique se o chipset (chip responsável pelo controle de diversos dispositivos de entrada e saída) da placa-mãe que pretende comprar é moderno. Exemplos: A320, B450, X470, H310, X570, etc;",
            "Fique atento se o socket (local onde o processador será instalado) da placa-mãe é compatível com o processador que prete" +
                    "nde comprar. Cada geração de processadores possui compatibilidade com um tipo específico de socket. Exemplo: AM4, para processadores AMD, LGA 1151, para processadores Intel;",
            "Também fique atento ao fator de forma (Form factor) da placa-mãe: ATX, Micro ATX, Mini ITX, etc."
        ),
        arrayOf(
            "Prefira memórias do padrão DDR4 (Double-Data-Rate), elas apresentam 50% de aumento de desempenho e 40% de economia de energia em comparação com o padrão DDR3 anterior;",
            "Dê preferência a comprar ao menos um par de memórias RAM com o mesmo tamanho, frequência e temporizações para usá-las em dual channel (o processador efetua a comunicação com os dois canais de memória ao mesmo tempo, dobrando a largura de dados do barramento);",
            "Se não pretender usar o computador para nenhum trabalho exigente, dois módulos de 4GB já são o suficiente, totalizando 8GB de memória RAM;",
            "Por outro lado, se pretende usar o computador para trabalhos exigentes como edição de vídeos, modelagem 3D ou jogos pesados, o ideal é usar dois módulos de 8GB, totalizando 16GB de memória RAM;",
            "Outro fator importante para usuários mais exigentes é a frequência da memória RAM, variando geralmente entre 2400 e 3200 MHZ, quanto maior a frequência melhor."
        ),
        arrayOf(
            "Não é necessário ter uma placa de vídeo dedicada para usar um computador, existem processadores com placas de vídeo integrada, então, se você não vai usar seu PC para jogar jogos muito exigentes ou trabalhos profissionais, opte por um bom processador com placa de vídeo integrada;\n" +
                    "Por outro lado, se pretende realizar trabalhos profissionais de edição de vídeos, modelagem 3D ou jogar jogos exigentes, vai precisar de uma placa de vídeo dedicada;",
            "Opte por modelos mais novos e que possuam amplo suporte pelas fabricantes AMD e NVIDIA.",
            "Exemplos AMD: RX 5500XT, RX 5600XT, RX 5700XT, etc;",
            "Exemplos NVIDIA: GTX 1650 Super, GTX 1660 Super, RTX 2060, etc;",
            "Dê preferência a placas de vídeo com 4GB de VRAM (memória de vídeo) ou mais."
        ),
        arrayOf(
            "Existem dois tipos principais de armazenamento: HDD (Hard Disk Drive) e" +
                    " SSD (Solid State Drive). Os SSDs NVMe (Non-Volatile Memory express) são mais" +
                    " rápidos que os SSDs convencionais que usam barramento SATA, que por sua vez são" +
                    " mais rápidos que os HDDs; sendo assim, prefira SSDs NVMe ou SSDs SATA para instalar " +
                    "seu Sistema Operacional e ferramentas de trabalho e os HDDS para armazenar outros arquivos;",
            "Existem discos de armazenamento de vários tamanhos, desde SSDs de 128GB até HDDs de 4TB ou" +
                    " mais. Uma boa configuração de entrada seria um SSD NVMe ou SATA de 128GB, para instalar" +
                    " o Sistema Operacional, e um HDD de 1TB, para armazenar outros arquivos"
        ),
        arrayOf(
            "Escolher o monitor certo depende muito do uso que você pretende de dar para ele;",
            "Se você é um usuário que não joga e prioriza qualidade de imagem e cores, ou até mesmo um profissional de edição de vídeos, correção de imagens, entre outros, opte por um monitor com taxa de atualização de 60Hz e painel do tipo IPS, de preferência, ou VA;",
            "Por outro lado, se você joga e prioriza taxa de resposta e frequência de atualização do monitor, opte por um monitor com 1 ms (milisegundo) de resposta, taxa de atualização de 120Hz ou mais e painel TN;",
            "Entretanto, se quiser o melhor dos dois mundos e tiver dinheiro para gastar, escolha um monitor com painel IPS, baixo tempo de resposta e alta taxa de atualização."
        ),
        arrayOf(
            "Existem três tipos principais de teclado: o mecânico, o semi-mecânico e o membrana;",
            "O teclado mecânico é caracterizado pelo funcionamento individual de cada uma das teclas," +
                    " permitindo maior capacidade de precisão, resposta mais rápida ao toque e maior durabilidade;" +
                    " é um ótimo teclado para quem escreve muito ou joga;",
            "Os teclados semi-mecânicos têm um funcionamento híbrido: as teclas de maior uso são mecânicas " +
                    "e as de menor uso são de acionamento por membrana;" +
                    "Por outro lado, nos teclados de membrana o acionamento se dá através de uma membrana plástica " +
                    "de três camadas, que se entende por baixo das teclas. Como o acionamento simultâneo é " +
                    "quase inexistente, pode ocorrer uma confusão de ordem das teclas pressionadas, além disso" +
                    " eles não possuem tanta resistência quando comparados aos outros modelos.",
            "Sendo assim, se você escreve ou joga muito e tem dinheiro para investir um pouco mais, compre um teclado mecânico."
        ),
        arrayOf(
            "Dependendo do seu uso, você pode precisar de um mouse com um DPI (Dots Per Inch) maior, ou seja, uma maior sensibilidade de movimento, causando uma maior precisão;",
            "Para uso doméstico, um mouse com alto DPI não faz muito sentido, mas sim para usuários que jogam games exigentes quanto a precisão e sensibilidade de movimento."
        ),
        arrayOf(
            "Preste atenção se o headset que você pretende comprar vem com um fone de ouvido estéreo ou surround. O som estéreo (2.0) tem duas saídas, o que não garante uma performance perfeita do áudio. Já a tecnologia surround tem duas opções: 5.1 e 7.1, em que o primeiro número corresponde ao número de canais de som;",
            "Se você usa seu computador em um ambiente muito barulhento, pode optar por um headset com cancelamento de ruído;",
            "A potência do headset é o que determina quão alto o som ficará. Ou seja, se você quer ouvir o áudio bem alto, deve procurar pelos fones com maior potência. Um bom fone começa com potência de 50 miliwatts e os mais potentes podem chegar à 150 miliwatts."
        ),
        arrayOf(
            "Prefira fontes de alimentação com certificação 80 Plus; esta certificação é atribuída a fontes de alimentação com um rendimento (relação entre a potência consumida e a energia fornecida) maior ou igual a 80%.",
            "Existem 6 tipos de certificação 80 Plus: Standard (80% de rendimento), Bronze (82% de rendimento), Silver (85% de rendimento), Gold (87% de rendimento), Platinum (90% de rendimento) e Titanium (94% de rendimento). Sendo assim, uma fonte de 500W com certificação 80 Plus Standard entrega cerca de 400W de potência.",
            "Verifique se a potência real da fonte de alimentação é suficiente para sua configuração de PC;",
            "Fique atento ao fator de forma da fonte de alimentação (Form Factor): ATX, Micro ATX e MINI ITX;",
            "Existem três tipo de cabeamento de fontes de alimentação: modular, em que todos os cabos não estão fixamente conectados à fonte, semi-modular, em que alguns cabos são fixos e outros não, e a não-modular, a qual todos os cabos são fixamente conectados."
        ),
        arrayOf(
            "O tamanho e a forma de um gabinete são determinados pelo fator de forma da placa-mãe, podendo ser ATX (Advanced Technology Extended), EATX (Extended ATX) Micro ATX e Mini ITX (Information Technology eXtended), ou seja é o fator de forma da sua placa-mãe que define que tipo de gabinete você deve comprar;",
            "Prefira gabinetes com um bom fluxo de ar (air flow), ou seja, gabinetes que tenham entradas e saídas de ar bem posicionadas e que suportem uma ou mais ventoinhas;",
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_part_activity)
        var pos = intent.getIntExtra("id", 0)

        compName.text = names[pos]
        definition_text.text = definitions[pos]


        layoutManager = LinearLayoutManager(this)
        tips_list.layoutManager = layoutManager

        adapter = TipsRecyclerAdapter(tips[pos])
        tips_list.adapter = adapter


        imageView2.setImageResource(photos[pos])
        partList.setOnClickListener {
            val intent = Intent(this, ListProductActivity::class.java)
            intent.putExtra("peca", key[pos])
            startActivity(intent)
        }
    }


}