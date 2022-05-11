package com.geektach.newsapp42.News;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.geektach.newsapp42.OnItemClickListener;
import com.geektach.newsapp42.R;
import com.geektach.newsapp42.databinding.ItemNewsBinding;
import com.geektach.newsapp42.models.Article;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private ArrayList<Article> list;
    private OnItemClickListener onItemClickListener;

    public NewsAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public void setList(ArrayList<Article> arrayList) {
        this.list = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(Article article) {
        list.add(0, article);
        notifyItemInserted(0);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public Article getItem(int position) {
        return list.get(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addItems(List<Article> list) {
        Comparator<Article> comparator = Comparator.comparing(new Function<Article, Long>() {
            @Override
            public Long apply(Article article) {
                return article.getDate();
            }
        });
        Collections.reverse(this.list);
        this.list.sort(comparator);
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemNewsBinding binding;
        private TextView textView;

        public ViewHolder(@NonNull ItemNewsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            textView = itemView.findViewById(R.id.textTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(getAdapterPosition());
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    onItemClickListener.onItemLongClick(getAdapterPosition());
                    new AlertDialog.Builder(view.getContext()).setTitle("Удаление")
                            .setMessage("Вы точно хотите удалить?")
                            .setPositiveButton("Нет", null)
                            .setNegativeButton("Да", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(view.getContext(), "Удалено", Toast.LENGTH_SHORT).show();
                                    list.remove(getAdapterPosition());
                                    notifyDataSetChanged();
                                }
                            }).show();
                    return true;
                }
            });
        }

        public void bind(Article article) {
            textView.setText(article.getText());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm, d MM yyyy", Locale.ROOT);
            String data = String.valueOf(simpleDateFormat.format(article.getDate()));
            binding.tvTimes.setText(data);
        }
    }
}
