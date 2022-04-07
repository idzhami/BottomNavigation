package id.idzhami.tutorialrecycleviewkotlin.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import id.idzhami.tutorialrecycleviewkotlin.R
import id.idzhami.tutorialrecycleviewkotlin.model.dataModel
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*






class RecycleViewAdapter (

    private val context: Context,
    private var resultTransaction: List<dataModel>

) : RecyclerView.Adapter<RecycleViewAdapter.ViewHolderTransaction>() {
    private val TAG = javaClass.simpleName
    companion object {
        private const val VIEW_TYPE_DATA = 0;
        private const val VIEW_TYPE_PROGRESS = 1;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderTransaction {
        return when (viewType) {
            VIEW_TYPE_DATA -> {//inflates row layout
                val view = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.item_list, parent, false)
                ViewHolderTransaction(view)
            }
            VIEW_TYPE_PROGRESS -> {//inflates progressbar layout
                val view = LayoutInflater.from(parent?.context)
                    .inflate(R.layout.support_simple_spinner_dropdown_item, parent, false)
                ViewHolderTransaction(view)
            }
            else -> throw IllegalArgumentException("Different View type")
        }
    }

    override fun getItemCount(): Int = resultTransaction.size

    fun refreshAdapter(resultTransaction: List<dataModel>) {
        this.resultTransaction = resultTransaction
        notifyItemRangeChanged(0, this.resultTransaction.size)
    }


    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(
        holder: RecycleViewAdapter.ViewHolderTransaction,
        position: Int
    ) {
        val txtName: TextView
        val txtDate: TextView
        val txtAmount: TextView
        val LY_CONTAINER : LinearLayout

        txtName = holder.itemView.findViewById(R.id.TXT_NAME) as TextView
        txtDate = holder.itemView.findViewById(R.id.TXT_DATE) as TextView
        txtAmount = holder.itemView.findViewById(R.id.TXT_AMOUNT) as TextView

        LY_CONTAINER = holder.itemView.findViewById(R.id.LY_CONTAINER) as LinearLayout
        if (holder.itemViewType == VIEW_TYPE_DATA) {
            val resultItem = resultTransaction[position]
            txtName.text = resultItem.Name
            txtDate.text = resultItem.Date
            txtAmount.text =formatCurrency(resultItem.Amount.toBigDecimal())

            LY_CONTAINER.setOnClickListener {
                Toast.makeText(context,
                    resultItem.Name,
                    Toast.LENGTH_SHORT
                ).show()

            }

        }
    }
    override fun getItemViewType(position: Int): Int {
        return if (resultTransaction[position] == null) {
            VIEW_TYPE_PROGRESS
        } else {
            VIEW_TYPE_DATA
        }
    }
    inner class ViewHolderTransaction(itemView: View?) : RecyclerView.ViewHolder(itemView!!)

    private fun formatCurrency(amount: BigDecimal): String {
        val COUNTRY = "ID"
        val LANGUAGE = "in"
        var str: String = NumberFormat.getCurrencyInstance(Locale(LANGUAGE, COUNTRY)).format(amount)
        str = str.replace("Rp", "Rp ")
        return str
    }
}