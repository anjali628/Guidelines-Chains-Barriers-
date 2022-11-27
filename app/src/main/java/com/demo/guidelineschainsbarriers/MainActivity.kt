package com.demo.guidelineschainsbarriers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import com.demo.guidelineschainsbarriers.ui.theme.GuidelinesChainsBarriersTheme
import kotlinx.coroutines.NonDisposableHandle.parent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuidelinesChainsBarriersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   //GuidelinesExample()
                    //CreateBarrierExample()
                    CreateChainsExample()
                }
            }
        }
    }
}

@Composable
fun GuidelinesExample(){

    ConstraintLayout(modifier=Modifier.fillMaxWidth()) {

        val (text1)=createRefs()
        val createGuidelineTop = createGuidelineFromTop(40.dp)

        Text(text = "Some contents",
            modifier = Modifier.constrainAs(text1){
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(createGuidelineTop)

            }
        )

    }
}

@Composable
fun CreateBarrierExample(){

    ConstraintLayout (
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp)
            ){

        val (text1,text2,text3) = createRefs()
        val barrierEnd = createEndBarrier(
            text1,text2
        )

        Text(text = "text one contents",
        modifier=Modifier.constrainAs(text1){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        })

        Text(text = "text two",
            modifier=Modifier.constrainAs(text2){
                start.linkTo(parent.start)
                top.linkTo(text1.bottom)
            })

        Text(text = "text three",
            modifier=Modifier.constrainAs(text3){
                start.linkTo(barrierEnd)
                top.linkTo(text1.bottom)
            })

    }
}


@Composable
fun CreateChainsExample(){

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ){
        val (text1, text2, text3) = createRefs()

        createHorizontalChain(text1,
            text2,
            text3,
        chainStyle = ChainStyle.SpreadInside)

        //createVerticalChain()

        Text(text = "text1",
        modifier = Modifier.constrainAs(text1){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
        })

        Text(text = "text2",
            modifier = Modifier.constrainAs(text2){
                start.linkTo(text1.end)
                top.linkTo(text1.top)
                bottom.linkTo(text1.bottom)
            })

        Text(text = "text3",
            modifier = Modifier.constrainAs(text3){
                start.linkTo(text2.end)
                top.linkTo(text2.top)
                bottom.linkTo(text2.bottom)
            })
    }

}
