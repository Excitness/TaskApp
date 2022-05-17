package com.excitness.taskapp.ui.bord;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.excitness.taskapp.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private final String[] img = {"https://whatsism.com/uploads/posts/2020-03/1584029770_unnamed.jpg",
            "https://mskc.pro/wp-content/uploads/2016/05/Zametki.png",
            "https://smart-poliv.ru/wp-content/uploads/2017/03/preimuschestva.png",
            "https://acegif.com/wp-content/gifs/udachi-21.gif",
    };

    private final String[] title = {
            "Здравствуйте!",
            "Кратко о приложении",
            "Преимущетсва",
            "Желаю удачи в использовании приложения"
    };
    private final String[] desc = {
            "Вы попали в приложение Заметки",
            "Это приложение поможет вам сохранить какую-либо информацию",
            "В этом приложении вы можете записать и хранить любую информацию",
            "Давайте начнём..."
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vp, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(img[position], title[position], desc[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView desc;
        private final ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv_vp);
            desc = itemView.findViewById(R.id.description_tv_vp);
            img = itemView.findViewById(R.id.img_vp);
        }

        public void onBind(String img, String title, String desc) {
            this.title.setText(title);
            this.desc.setText(desc);
            Glide.with(this.img).load(img).into(this.img);
        }
    }
}
