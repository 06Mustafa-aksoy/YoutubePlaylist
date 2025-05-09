import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sleepwalker.youtubeplaylistlerapp.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class PlaylistAdapter(tumOynatmaListeleri: List<PlaylistData.Items>?) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {

    var oynatmaListeleri = tumOynatmaListeleri

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val tekSatirPlaylist = inflater.inflate(R.layout.tek_satir_playlist, parent, false)
        return PlaylistViewHolder(tekSatirPlaylist)
    }

    override fun getItemCount(): Int {
        return oynatmaListeleri!!.size
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val oanOlusturulanSatir = oynatmaListeleri?.get(position)
        holder.setData(oanOlusturulanSatir, position)
    }

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tekSatirPlaylist = itemView as CardView
        private val playListTitle = tekSatirPlaylist.findViewById<TextView>(R.id.tvListeBaslik)
        private val playListResim = tekSatirPlaylist.findViewById<CircleImageView>(R.id.circleResim)

        fun setData(oanOlusturulanSatir: PlaylistData.Items?, pos: Int) {
            playListTitle.text = oanOlusturulanSatir?.snippet?.title
            Picasso.get().load(oanOlusturulanSatir?.snippet?.thumbnails?.high?.url).into(playListResim)
        }
    }
}
