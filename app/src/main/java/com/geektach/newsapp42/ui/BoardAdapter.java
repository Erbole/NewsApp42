package com.geektach.newsapp42.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geektach.newsapp42.R;

import java.util.PrimitiveIterator;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] titles = new String[]{"Качок Доге и плачущий Чимс", "Танцующие носильщики гробов", "Наташ, ты спишь?",};
    private String[] disc = new String[]{"Мемом с собаками породы сиба-ину пользователи сравнивали настоящий момент и прошлое. Победа всегда на стороне Доге, и он, как правило, олицетворяет прошедшие времена.", "Танцующие с гробом темнокожие парни были популярны практически весь год. Первые смешные видео с их участием появились в конце февраля. Популярность они набрали в связи с новостями о коронавирусе.", "Мем «Наташ, ты спишь» стал абсолютным хитом в апреле: с его помощью шутили про коронавирус, самоизоляцию, цифровые пропуска. Потом, в течение года, используя этот шаблон, пользователи обращались к самым разным темам. Этот мем — народный, по мнению Максима Корнева, хотя его и быстро «затаскали» все, кому не лень."};
    private int[] images = new int[]{R.drawable.photo_1, R.drawable.photo_2, R.drawable.photo_3};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pager_board, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textDisc;
        private TextView textTitle;
        private Button btnStart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textDisc = itemView.findViewById(R.id.textDisc);
            textTitle = itemView.findViewById(R.id.textTitle);
            btnStart = itemView.findViewById(R.id.sliderDots);
            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).popBackStack();
                }
            });
        }

        public void bind(int position) {

            textDisc.setText(disc[position]);
            textTitle.setText(titles[position]);
            if (position == titles.length - 1) btnStart.setVisibility(View.VISIBLE);
            else btnStart.setVisibility(View.INVISIBLE);
        }
    }
}
