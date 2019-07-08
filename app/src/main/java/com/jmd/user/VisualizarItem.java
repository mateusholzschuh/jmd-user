package com.jmd.user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VisualizarItem extends AppCompatActivity {

    TextView aliasnome, aliaspreco, aliasvalidade, aliasdescricao;
    ImageView aliasfoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_item);

        aliasnome = findViewById(R.id.textNome);
        aliaspreco = findViewById(R.id.textPreco);
        aliasvalidade = findViewById(R.id.textValidade);
        aliasdescricao = findViewById(R.id.textDescricao);

        aliasfoto = (ImageView) findViewById(R.id.imageItem);


    }
}
