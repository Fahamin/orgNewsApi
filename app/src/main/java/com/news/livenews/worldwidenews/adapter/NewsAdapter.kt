import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.news.livenews.worldwidenews.interfaceall.ItemClickListener
import com.news.livenews.worldwidenews.databinding.NewsItemBinding
import com.news.livenews.worldwidenews.model.Articles


class ArticleAdapter(var list: List<Articles>, var itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setData(articles: Articles) {
            Glide
                .with(binding.newsImage.context)
                .load(articles.urlToImage)
                .centerCrop()
                .into(binding.newsImage)

            binding.newsTitle.text = articles.title
            binding.publishedTime.text = articles.publishedAt

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list[position].let {
            holder.setData(it)
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            itemClickListener.itemClick(position)
        })
    }

    override fun getItemCount(): Int {
        return list.size
    }
}