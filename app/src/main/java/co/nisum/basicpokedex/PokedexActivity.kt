package co.nisum.basicpokedex

import androidx.navigation.findNavController
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import co.nisum.basicpokedex.base.BaseActivity
import co.nisum.basicpokedex.databinding.ActivityPokedexBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PokedexActivity : BaseActivity<ActivityPokedexBinding>() {


    override fun inflateView(inflater: LayoutInflater) =
        ActivityPokedexBinding.inflate(inflater)


    override fun setUI() {
        binding.apply {
           findNavController(R.id.nav_host_fragment)
        }
    }




}