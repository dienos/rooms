package com.jth.rooms.view

import android.os.Bundle
import android.widget.CompoundButton
import androidx.lifecycle.Observer
import com.jth.rooms.binding.BaseBindingActivity
import com.jth.rooms.databinding.ActivityMainBinding
import com.jth.rooms.repo.MainRepository
import com.jth.rooms.repo.model.RoomType
import com.jth.rooms.repo.model.SellingType
import com.jth.rooms.usecase.MainActivityUseCase
import com.jth.rooms.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.jth.rooms.repo.RoomDataFactory
import com.jth.rooms.repo.model.Room
import com.jth.rooms.repo.model.RoomPositionalDataSource


class MainActivity : BaseBindingActivity<ActivityMainBinding>() {

    override fun getLayoutResId(): Int {
        return com.jth.rooms.R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = MainRepository.getInstance()
        binding.lifecycleOwner = this
        binding.viewModel = MainViewModel(MainActivityUseCase(this), repo)
        (binding.viewModel as MainViewModel).makePagedList()
        initCheckBox()
        initAdapter()

    }

    private fun initCheckBox() {
        binding.checkOneRoom.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).roomTypeFilter.add(RoomType.ONE.type)
                } else {
                    if ((binding.viewModel as MainViewModel).roomTypeFilter.size == 1) {
                        binding.checkOneRoom.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).roomTypeFilter.remove(RoomType.ONE.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })

        binding.checkTwoRoom.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).roomTypeFilter.add(RoomType.TWO.type)
                } else {
                    if ((binding.viewModel as MainViewModel).roomTypeFilter.size == 1) {
                        binding.checkTwoRoom.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).roomTypeFilter.remove(RoomType.TWO.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })

        binding.checkOp.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).roomTypeFilter.add(RoomType.OP.type)
                } else {
                    if ((binding.viewModel as MainViewModel).roomTypeFilter.size == 1) {
                        binding.checkOp.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).roomTypeFilter.remove(RoomType.OP.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })

        binding.checkApt.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).roomTypeFilter.add(RoomType.APT.type)
                } else {
                    if ((binding.viewModel as MainViewModel).roomTypeFilter.size == 1) {
                        binding.checkApt.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).roomTypeFilter.remove(RoomType.APT.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })

        binding.checkMonth.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).sellingTypeFilter.add(SellingType.MONTHLY.type)
                } else {
                    if ((binding.viewModel as MainViewModel).sellingTypeFilter.size == 1) {
                        binding.checkMonth.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).sellingTypeFilter.remove(SellingType.MONTHLY.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })

        binding.checkCharter.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).sellingTypeFilter.add(SellingType.CHARTER.type)
                } else {
                    if ((binding.viewModel as MainViewModel).sellingTypeFilter.size == 1) {
                        binding.checkCharter.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).sellingTypeFilter.remove(SellingType.CHARTER.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })

        binding.checkSell.setOnCheckedChangeListener(object :
            CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                if (isChecked) {
                    (binding.viewModel as MainViewModel).sellingTypeFilter.add(SellingType.TRADING.type)
                } else {
                    if ((binding.viewModel as MainViewModel).sellingTypeFilter.size == 1) {
                        binding.checkSell.isChecked = true
                    } else {
                        (binding.viewModel as MainViewModel).sellingTypeFilter.remove(SellingType.TRADING.type)
                    }
                }

                (binding.viewModel as MainViewModel).roomDataFactory?.postLiveData?.value?.invalidate()
                (binding.viewModel as MainViewModel).makePagedList()
            }
        })
    }

    private fun initAdapter() {
        val adapter = RoomListAdapter(binding.viewModel as MainViewModel)
        room_list.adapter = adapter

        (binding.viewModel as MainViewModel).getPagedItem()
            .observe(this, Observer { items ->
                if (items != null && items.isNotEmpty()) {
                    adapter.submitList(items)
                }
            })
    }
}