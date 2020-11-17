package es.crmone.app

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.navigation.fragment.NavHostFragment
import es.crmone.app.common.BaseFragment
import es.crmone.app.databinding.FragmentMainBinding

class MainFragment: BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentMainBinding.bind(view)

        val navHome = NavHostFragment.create(R.navigation.home)
        val navCalendar = NavHostFragment.create(R.navigation.calendar)
        val navClient = NavHostFragment.create(R.navigation.client)
        val navMap = NavHostFragment.create(R.navigation.map)



        val transaction = childFragmentManager.beginTransaction()

        //Añadir navegación de los diferentes fragment
        transaction.add(R.id.flContent, navHome, navHome.javaClass.name)
        transaction.add(R.id.flContent, navCalendar, navCalendar.javaClass.name)
        transaction.add(R.id.flContent, navClient, navClient.javaClass.name)
        transaction.add(R.id.flContent, navMap, navMap.javaClass.name)


        //Ocultamos dejando visible solo el principal.
        transaction.hide(navCalendar)
        transaction.hide(navClient)
        transaction.hide(navMap)
        transaction.show(navHome).commitNow()


        //Cada uno de los enlaces del menu enEscucha al click.
        binding.navView.findViewById<LinearLayout>(R.id.ll_home).setOnClickListener {
            binding.drawerLayout.close()
            val transaction = childFragmentManager.beginTransaction()
            transaction.hide(navCalendar)
            transaction.hide(navClient)
            transaction.hide(navMap)
            transaction.show(navHome).commit()
        }

        binding.navView.findViewById<LinearLayout>(R.id.ll_calendar).setOnClickListener {
            binding.drawerLayout.close()
            val transaction = childFragmentManager.beginTransaction()
            transaction.hide(navHome)
            transaction.hide(navClient)
            transaction.hide(navMap)
            transaction.show(navCalendar).commit()
        }

        binding.navView.findViewById<LinearLayout>(R.id.ll_client).setOnClickListener {
            binding.drawerLayout.close()
            val transaction = childFragmentManager.beginTransaction()
            transaction.hide(navCalendar)
            transaction.hide(navHome)
            transaction.hide(navMap)
            transaction.show(navClient).commit()
        }


        binding.navView.findViewById<LinearLayout>(R.id.ll_map).setOnClickListener {
            binding.drawerLayout.close()
            val transaction = childFragmentManager.beginTransaction()
            transaction.hide(navCalendar)
            transaction.hide(navHome)
            transaction.hide(navClient)
            transaction.show(navMap).commit()
        }


        /*BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navClientFragment -> {
                    findNavController().navigate(ClientFragment)
                }

                else -> false
            }
        }*/

        /*binding.navClientFragment.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToDetail())
        }*/

        /*binding.btAbrirPerfil.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeToProfile())
        }*/

    }


}