package com.jmd.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmd.user.modelo.Mercado;
import com.jmd.user.modelo.Promocao;
import com.squareup.picasso.Picasso;

public class VisualizarItem extends AppCompatActivity {

    TextView aliasnome, aliaspreco, aliasvalidade, aliasdescricao;
    ImageView aliasfoto;

    Promocao promocao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_item);

        aliasnome = findViewById(R.id.textNome);
        aliaspreco = findViewById(R.id.textPreco);
        aliasvalidade = findViewById(R.id.textValidade);
        aliasdescricao = findViewById(R.id.textDescricao);

        aliasfoto = (ImageView) findViewById(R.id.imageItem);

        Intent i = getIntent();
        //String muuid = i.getStringExtra("promocao");
        promocao = (Promocao)i.getSerializableExtra("promocao");

        if (i!=null && promocao!=null)
        {

        }
        else {
            finish();
        }

        aliasnome.setText(promocao.getNome());
        aliaspreco.setText(String.format("R$ %.2f", promocao.getPreco()));
        aliasvalidade.setText(Uteis.formataDataParaString(promocao.getValidade()));
        aliasdescricao.setText(promocao.getDescricao());

        Picasso.with(this).load(promocao.getImagem())
                .centerCrop().resize(500, 500)
                .into(aliasfoto);


    }
}
