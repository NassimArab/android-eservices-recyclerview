package android.eservices.recyclerview;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.net.URI;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<GameViewModel> items;
    private GameActionInterface gameActionInterface;
    public GameAdapter(List<GameViewModel> items) {
        this.items = items;
    }
    public GameAdapter(GameActionInterface gameActionInterface,List<GameViewModel> items) {
        this.gameActionInterface=gameActionInterface;
        this.items = items;
    }



    @Override
    public GameAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        // Create and return the ViewHolder
        return new GameViewHolder(view,gameActionInterface);
    }


    @Override
    public void onBindViewHolder(@NonNull GameAdapter.GameViewHolder holder, int position) {

        holder.bind( items.get(position));
    }
    @Override
    public int getItemCount() {
        return items.size();
    }

    class GameViewHolder extends RecyclerView.ViewHolder {

        private TextView gameTitle;
        private TextView gameDescription;
        private ImageView gameImage;
        private ImageButton gameInfoButton;
        private ImageButton gameImageButton;
        private GameViewModel gameViewModel;
        private GameActionInterface gameActionInterface;


        public GameViewHolder(@NonNull final View itemView, final GameActionInterface gameActionInterface) {
            super(itemView);
            this.gameActionInterface = gameActionInterface;
            gameTitle = itemView.findViewById(R.id.title_textview);
            gameDescription = itemView.findViewById(R.id.description_textview);
            gameImage = itemView.findViewById(R.id.icon_imageview);
            gameInfoButton = itemView.findViewById(R.id.info_button);
            gameImageButton = itemView.findViewById(R.id.game_button);

            gameInfoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });

            gameImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    gameActionInterface.onGameClicked(gameViewModel.getTitle());
                }
            });

        }

        public void bind(GameViewModel gameViewModel){
            this.gameViewModel = gameViewModel;
            gameTitle.setText(gameViewModel.getTitle());
            gameDescription.setText(gameViewModel.getDescription());

            Glide.with(itemView)
                    .load(gameViewModel.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.borderlands)
                    .into(gameImage);

        }
    }

}