package com.jmd.user;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jmd.user.adaptadores.PromocaoAdapter;
import com.jmd.user.modelo.Mercado;
import com.jmd.user.modelo.Promocao;
import com.jmd.user.persistencia.PromocaoDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListPromo extends AppCompatActivity {

    RecyclerView aliaslista;
    Promocao promocao;
    List<Promocao> listPromocoes;
    protected PromocaoAdapter adapter;
    Mercado mercado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_promo);

        aliaslista = findViewById(R.id.ListID);
        promocao = new Promocao();

        listPromocoes = new ArrayList<>();

        Intent i = getIntent();
        String muuid = i.getStringExtra("mercado");
        if (i!=null && muuid!=null)
        {
            mercado = new Mercado();
            mercado.setUuid(muuid);
        }
        else {
            finish();
        }


        aliaslista.setLayoutManager(new LinearLayoutManager(this));
        aliaslista.setItemAnimator(new DefaultItemAnimator());

        // TODO: FETCH ALL RECORDS FROM FIREBASE HERE
        //listPromocoes = new ArrayList<>();
        PromocaoDAO.getInstance().buscarTodas(mercado.getUuid(), recuperaDados());
        aliaslista.setAdapter(adapter = new PromocaoAdapter(this, listPromocoes, onClickPromocao()));
    }

    private PromocaoAdapter.PromocaoOnClickListener onClickPromocao() {
        return new PromocaoAdapter.PromocaoOnClickListener() {
            @Override
            public void onClickPromocao(PromocaoAdapter.PromocoesViewHolder holder, int idx) {

                promocao = listPromocoes.get(idx);

                // envia objeto clicado para o manter...
                Intent intent = new Intent(getBaseContext(), VisualizarItem.class);
                intent.putExtra("promocao", promocao);
                startActivity(intent);
            }
        };
    }

    private ValueEventListener recuperaDados() {
        return new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPromocoes.clear();

                for (DataSnapshot promoSnapshot : dataSnapshot.getChildren()) {
                    Promocao promo = promoSnapshot.getValue(Promocao.class);
                    promo.setUid(promoSnapshot.getKey());
                    listPromocoes.add(promo);
                }
                Collections.sort(listPromocoes);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), "Ops! Ocorreu um erro.", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
