package com.jmd.user.persistencia;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jmd.user.modelo.Promocao;

public class PromocaoDAO {
    private static PromocaoDAO instance;

    //private static FirebaseUser user;
    private static FirebaseDatabase database;
    private DatabaseReference ref;

    private String PROMO = "promocoes";

    private PromocaoDAO() {
        //user = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
    }

    public static PromocaoDAO getInstance() {
        if (instance == null)
            instance = new PromocaoDAO();
        return instance;
    }


    /**
     * <h1>Buscar uma promoção pelo <i>UUID</i> dela.</h1>
     *
     */
    public void buscar (String mercadoUUID, String uuidPromo, ValueEventListener listener) {
        ref = database.getReference(PROMO).child(mercadoUUID);
        ref.child(uuidPromo).addListenerForSingleValueEvent(listener);
    }

    /**
     * <h1>Buscar todas as promoções do mercado com o <b>UUID</b> passado como parâmetro.</h1>
     * @param uuidMercado ID do mercado
     * @param listener Callback da busca
     */
    public void buscarTodas (String uuidMercado, ValueEventListener listener) {
        ref = database.getReference(PROMO).child(uuidMercado);
        ref.addListenerForSingleValueEvent(listener);
    }

    /**
     * <h1>Buscar todas as promoções do mercado com o <i>UUID</i> passado como parâmetro, e manter sincronizado com o firebase.</h1>
     * @param uuidMercado ID do mercado
     * @param listener Callback da busca
     */
    public void buscarTodasSincronizado (String uuidMercado, ValueEventListener listener) {
        ref = database.getReference(PROMO).child(uuidMercado);

        ref.addValueEventListener(listener);
    }
}
