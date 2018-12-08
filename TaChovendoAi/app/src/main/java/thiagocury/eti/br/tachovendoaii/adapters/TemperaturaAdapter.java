package thiagocury.eti.br.tachovendoaii.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import thiagocury.eti.br.tachovendoaii.helper.Padronizacao;
import thiagocury.eti.br.tachovendoaii.R;
import thiagocury.eti.br.tachovendoaii.model.Temperatura;

/**
 * Created by thiagocury on 03/01/2018.
 */

public class TemperaturaAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Temperatura> temperaturas;
    private static ClickListener clickListener;

    public TemperaturaAdapter(Context context, ArrayList<Temperatura> temperaturas) {
        this.context = context;
        this.temperaturas = temperaturas;
    }

    public void setTemperaturas(ArrayList<Temperatura> temperaturas) {
        this.temperaturas = temperaturas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_temperatura, parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder hold = (ViewHolder) holder;
        Temperatura t = temperaturas.get(position);
        if(t.getBairro() == null){
            hold.tvBairro.setText("Bairro não foi fornecido!");
        } else{
            hold.tvBairro.setText("Bairro: "+t.getBairro());
        }
        if(t.getTemperaturaExterna() == null){
            hold.tvTemperaturaExterna.setText("Temperatura Externa não foi fornecida!");
        } else{
            hold.tvTemperaturaExterna.setText("Temperatura Externa: "+ Padronizacao.converterTemperatura(Double.parseDouble(t.getTemperaturaExterna()))+"˚C");
        }
        if(t.getSensacaoTermica() == null){
            hold.tvSensacaoTermica.setText("Sensação Térmica não foi fornecida!");
        } else{
            hold.tvSensacaoTermica.setText("Sensação Térmica: "+Padronizacao.converterTemperatura(Double.parseDouble(t.getSensacaoTermica()))+"˚C");
        }
        if(t.getChuvaDiaria() == null){
            hold.tvChuvaDiaria.setText("Chuva Diária não foi fornecida!");
        } else{
            hold.tvChuvaDiaria.setText("Chuva diária: "+Padronizacao.converterTemperatura(Double.parseDouble(t.getChuvaDiaria()))+"ml");
        }

        hold.ivIcone.setImageResource(t.verificarIcone());
    }

    @Override
    public int getItemCount() {
        return temperaturas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView tvBairro;
        private final TextView tvTemperaturaExterna;
        private final TextView tvSensacaoTermica;
        private final TextView tvChuvaDiaria;
        private final ImageView ivIcone;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            tvBairro = itemView.findViewById(R.id.lc_tv_nome);
            tvTemperaturaExterna = itemView.findViewById(R.id.lc_tv_mensagem);
            tvSensacaoTermica = itemView.findViewById(R.id.lt_tv_sensacaotermica);
            tvChuvaDiaria = itemView.findViewById(R.id.lt_tv_chuvadiaria);
            ivIcone = itemView.findViewById(R.id.lt_iv_icone);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(),v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(),v);
            return true;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        TemperaturaAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
