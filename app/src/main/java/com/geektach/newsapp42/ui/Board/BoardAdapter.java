package com.geektach.newsapp42.ui.Board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geektach.newsapp42.R;
import com.geektach.newsapp42.databinding.PagerBoardBinding;
import com.geektach.newsapp42.models.Board;

import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private ArrayList<Board> list;

    public BoardAdapter() {
        list = new ArrayList<>();
        list.add(new Board("Качок Доге и плачущий Чимс",
                "Мемом с собаками породы сиба-ину пользователи сравнивали настоящий момент и прошлое. Победа всегда на стороне Доге, и он, как правило, олицетворяет прошедшие времена.",
                R.drawable.photo_1));
        list.add(new Board("Танцующие носильщики гробов",
                "Танцующие с гробом темнокожие парни были популярны практически весь год. Первые смешные видео с их участием появились в конце февраля. Популярность они набрали в связи с новостями о коронавирусе.",
                R.drawable.photo_2));
        list.add(new Board("Наташ, ты спишь?",
                "Мем «Наташ, ты спишь» стал абсолютным хитом в апреле: с его помощью шутили про коронавирус, самоизоляцию, цифровые пропуска. Потом, в течение года, используя этот шаблон, пользователи обращались к самым разным темам. Этот мем — народный, по мнению Максима Корнева, хотя его и быстро «затаскали» все, кому не лень.",
                R.drawable.photo_3));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PagerBoardBinding binding = PagerBoardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textTitle;
        private TextView textDisc;
        private Button btn_start;

        private PagerBoardBinding binding;

        public ViewHolder(@NonNull PagerBoardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            textDisc = itemView.findViewById(R.id.textDisc);
            textTitle = itemView.findViewById(R.id.textTitle);
            btn_start = itemView.findViewById(R.id.sliderDots);
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Navigation.findNavController(view).popBackStack();
                }
            });
        }

        public void bind(int position) {
            Board board = list.get(position);
            binding.textDisc.setText(board.getDesc());
            binding.textTitle.setText(board.getTitle());
            binding.imageView.setImageResource(board.getImage());
            if (position == list.size() - 1) {
                btn_start.setVisibility(View.VISIBLE);
            } else {
                btn_start.setVisibility(View.INVISIBLE);
            }
        }
    }
}
