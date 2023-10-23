import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.data.DictionaryEntry
import com.example.dictionary.databinding.ItemDictionaryBinding

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    private var entries: List<DictionaryEntry> = emptyList()

    inner class ViewHolder(private val binding: ItemDictionaryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: DictionaryEntry) {
            binding.wordTextView.text = entry.word
            binding.phoneticTextView.text = entry.phonetic

            val meaningsStringBuilder = StringBuilder()

            for (meaning in entry.meanings) {
                meaningsStringBuilder.append("\n ${meaning.partOfSpeech}\n")

                for (definition in meaning.definitions) {
                    meaningsStringBuilder.append(" - ${definition.definition}\n")
                    if (definition.example != null) {
                        meaningsStringBuilder.append("   Example: ${definition.example}\n")
                    }
                }
            }
            binding.definitionTextView.text = meaningsStringBuilder.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDictionaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(entries[position])
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    fun setEntries(entries: List<DictionaryEntry>) {
        this.entries = entries
        notifyDataSetChanged()
    }
}
