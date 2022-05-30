package com.boyia.app.shell.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.boyia.app.shell.module.NavigationFragment

/**
 * 使用compose来编写关于页面
 */
class BoyiaAboutFragment: NavigationFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return ComposeView(requireContext()).apply { 
            setContent {
                buildLayout()
            }
        }
    }

    @Composable
    fun buildLayout() {
        Box(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White)
        ) {
            Column(
                    modifier = Modifier
                            .background(color = Color(0x08000000))
                            .fillMaxWidth()
                            .fillMaxHeight()
            ) {

                Column(
                        modifier = Modifier
                                .height(220.dp)
                                .fillMaxWidth()
                        //.background(Color.White)
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    Image(
                            painter = rememberImagePainter(data = "https://img1.baidu.com/it/u=4216761644,15569246&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500"),
                            modifier = Modifier.size(80.dp).align(alignment = Alignment.CenterHorizontally),
                            contentScale = ContentScale.Fit,
                            contentDescription = "",
                    )
                }
                //Spacer(modifier = Modifier.height(100.dp))
                TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()

                ) {
                    Text(text = "Version updata", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(1.dp))

                TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()

                ) {
                    Text(text = "Feature introduction", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(1.dp))

                TextButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()

                ) {
                    Text(text = "Feed back", color = Color.Black)
                }
            }
        }

    }
}