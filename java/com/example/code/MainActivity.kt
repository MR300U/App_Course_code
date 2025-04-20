package com.example.code

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        setContent {
            Greeting(

            )
        }
    }


    @Composable
    fun Greeting() {

        val scaffoldState = rememberScaffoldState()

        androidx.compose.material.Scaffold (
            bottomBar = {
                MyBottomBar()
            },
            scaffoldState = scaffoldState
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues = it),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchRow()
                Spacer(Modifier.size(15.dp))
                Banner()
                Spacer(Modifier.size(15.dp))
                Categories()
                PopularCourses()
                ItemList()
            }
        }

    }

    @Composable
    fun MyBottomBar() {
        val bottomMenuItemsList = prepareBottomMenu()
        val contextForToast = LocalContext.current.applicationContext

        var selectedItem by remember {
            mutableStateOf("profile")
        }

        BottomAppBar (
            cutoutShape = CircleShape,
            backgroundColor = Color(android.graphics.Color.parseColor("#f8f8f8")),
            elevation = 3.dp
            , modifier = Modifier.padding(bottom = 30.dp)
        ){
            bottomMenuItemsList.forEachIndexed { index, bottomMenuItem ->
                BottomNavigationItem(
                    selected = (selectedItem == bottomMenuItem.label),
                    onClick = {selectedItem = bottomMenuItem.label},
                    icon = {
                        Icon(
                            painter = bottomMenuItem.icon,
                            contentDescription = bottomMenuItem.label,
                            modifier = Modifier
                                .height(20.dp)
                                .width(20.dp)
                        )
                    },
                    label = {
                        Text(
                            text = bottomMenuItem.label,
                            modifier = Modifier.padding(4.dp)
                        )
                    },
                    alwaysShowLabel = true,
                    enabled = true
                )
            }
        }

    }


    @Composable
    fun prepareBottomMenu():List<BottomMenuItem>{
        val bottomMenuItemList = arrayListOf<BottomMenuItem>()

        bottomMenuItemList.add(
            BottomMenuItem(
                label = "اکسپلور",
                icon = painterResource(R.drawable.btn_1)
            )
        )

        bottomMenuItemList.add(
            BottomMenuItem(
                label = "علاقمندی ها",
                icon = painterResource(R.drawable.btn_2)
            )
        )

        bottomMenuItemList.add(
            BottomMenuItem(
                label = "دوره های من",
                icon = painterResource(R.drawable.btn_3)
            )
        )

        bottomMenuItemList.add(
            BottomMenuItem(
                label = "حساب",
                icon = painterResource(R.drawable.btn_4)
            )
        )

        return bottomMenuItemList

    }


    data class BottomMenuItem(
        val label : String,
        val icon : Painter
    )

    data class Items(
        val title : String ,
        val name : String ,
        val price : String ,
        val score : Double ,
        val picUrl : Int
    )


     @Composable
    fun ItemList() {

        val people:List<Items> = listOf(
            Items("اموزش برنامه نویسی موبایل(کاتلین) ","مهدی رضایی" , "رایگان",4.9 , R.drawable.pic1),
            Items("اموزش زبان برنامه نویسی پایتون","مهدی رضایی" , "رایگان",4.6 , R.drawable.pic2),
            Items("اموزش برنامه نویسی موبایل(فلاتر)","مهدی رضایی" , "رایگان",4.5 , R.drawable.pic1)
        )

         LazyRow(
             modifier = Modifier.fillMaxSize(),
             contentPadding = PaddingValues(16.dp),
             horizontalArrangement = Arrangement.spacedBy(16.dp),
             reverseLayout = true
         ) {
             items(people){item ->
                 Column(
                     modifier = Modifier
                         .height(250.dp)
                         .width(250.dp)
                         .shadow(3.dp , shape = RoundedCornerShape(10.dp))
                         .background(Color.White , shape = RoundedCornerShape(10.dp))
                         .fillMaxWidth()
                         .clickable {
                             println("clicked on : ${item.name}")
                         }
                 ) {

                     ConstraintLayout(
                         modifier = Modifier.height(IntrinsicSize.Max)
                     ) {
                         val (topImg , title , owner , ownerIcon , price , score , scoreIcon) = createRefs()

                         Image(
                             painter = painterResource(id = item.picUrl),
                             contentDescription = null ,
                             Modifier.fillMaxWidth()
                                 .height(180.dp)
                                 .constrainAs(topImg){
                                     top.linkTo(parent.top)
                                     start.linkTo(parent.start)
                                 }, contentScale = ContentScale.Crop
                         )

                         Text(
                             text = item.title ,
                             Modifier
                                 .background(Color(android.graphics.Color.parseColor("#90000000")))
                                 .fillMaxWidth()
                                 .padding(6.dp)
                                 .constrainAs(title){
                                     bottom.linkTo(topImg.bottom)
                                     start.linkTo(parent.start)
                                 },
                             fontWeight = FontWeight.Bold,
                             textAlign = TextAlign.Center,
                             color = Color.White,
                             fontSize = 14.sp
                         )
                         Image(
                             painter = painterResource(id = R.drawable.profile),
                             contentDescription = null,
                             modifier = Modifier
                                 .constrainAs(ownerIcon) {
                                     end.linkTo(parent.end) // تصویر را به سمت راست لینک می‌کند
                                     top.linkTo(topImg.bottom)
                                 }
                                 .padding(end = 16.dp, top = 16.dp) // فاصله از سمت راست
                         )

                         Text(
                             text = "${item.name}",
                             modifier = Modifier
                                 .constrainAs(owner) {
                                     end.linkTo(ownerIcon.start) // متن به چپ تصویر لینک می‌شود
                                     top.linkTo(ownerIcon.top)
                                     bottom.linkTo(ownerIcon.bottom)
                                 }
                                 .padding(end = 16.dp, top = 16.dp) // فاصله از سمت راست
                         )

                         Text(
                             text = "${item.price}",
                             fontWeight = FontWeight.Bold,
                             color = Color(android.graphics.Color.parseColor("#521c98")),
                             modifier = Modifier
                                 .constrainAs(price) {
                                     end.linkTo(ownerIcon.end) // قیمت به سمت راست تصویر لینک می‌شود
                                     top.linkTo(ownerIcon.bottom)
                                     bottom.linkTo(parent.bottom)
                                 }
                                 .padding(end = 16.dp, top = 8.dp) // فاصله از سمت راست
                         )

                         Text(
                             text = item.score.toString(),
                             fontWeight = FontWeight.Bold,
                             modifier = Modifier
                                 .constrainAs(score) {
                                     start.linkTo(parent.start) // امتیاز به سمت چپ والد لینک می‌شود
                                     top.linkTo(price.top)
                                     bottom.linkTo(price.bottom)
                                 }
                                 .padding(start = 16.dp) // فاصله از سمت چپ
                         )

                         Image(
                             painter = painterResource(id = R.drawable.star),
                             contentDescription = null,
                             modifier = Modifier
                                 .constrainAs(scoreIcon) {
                                     start.linkTo(score.end) // آیکون ستاره به راست امتیاز لینک می‌شود
                                     top.linkTo(score.top)
                                     bottom.linkTo(score.bottom)
                                 }
                                 .padding(start = 8.dp) // فاصله از سمت چپ
                         )



                     }

                 }

             }
         }

    }

    @Composable
    fun PopularCourses() {
        Row(
            modifier = Modifier
                .padding(top = 24.dp , start = 16.dp , end = 16.dp)
        ) {

            Text(
                text = "دیدن همه",
                color = Color(android.graphics.Color.parseColor("#521c98")) ,
                fontSize = 16.sp ,
                fontWeight = FontWeight.SemiBold ,
                textAlign = TextAlign.Left
            )

            Text(
                text = "دوره های محبوب",
                color = Color.Black ,
                fontSize = 20.sp ,
                fontWeight = FontWeight.SemiBold ,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right
            )

        }
    }

    @Composable
    fun Categories() {

        Row(
            modifier = Modifier
                .padding(top = 24.dp , start = 16.dp , end = 16.dp)
        ) {

            Text(
                text = "دیدن همه",
                color = Color(android.graphics.Color.parseColor("#521c98")) ,
                fontSize = 16.sp ,
                fontWeight = FontWeight.SemiBold ,
                textAlign = TextAlign.Left
            )

            Text(
                text = "دسته بندی ها",
                color = Color.Black ,
                fontSize = 20.sp ,
                fontWeight = FontWeight.SemiBold ,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Right
            )

        }

        Row(
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp , start = 16.dp , end = 16.dp)
        ) {
            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat1),
                    contentDescription = null ,
                    modifier = Modifier
                        .padding(top = 8.dp , bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp)
                )

                Text(
                    text = "کسب و کار",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color(android.graphics.Color.parseColor("#521c98"))
                )
            }

            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat2),
                    contentDescription = null ,
                    modifier = Modifier
                        .padding(top = 8.dp , bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp)
                )

                Text(
                    text = "خلاقیت",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color(android.graphics.Color.parseColor("#521c98"))
                )
            }

            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat3),
                    contentDescription = null ,
                    modifier = Modifier
                        .padding(top = 8.dp , bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp)
                )

                Text(
                    text = "کدنویسی",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color(android.graphics.Color.parseColor("#521c98"))
                )
            }

            Column(
                modifier = Modifier.weight(0.25f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cat4),
                    contentDescription = null ,
                    modifier = Modifier
                        .padding(top = 8.dp , bottom = 4.dp)
                        .background(
                            color = Color(android.graphics.Color.parseColor("#f0e9fa")),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(16.dp)
                )

                Text(
                    text = "بازی",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 1.dp),
                    color = Color(android.graphics.Color.parseColor("#521c98"))
                )
            }
        }
    }

    @Composable
    fun Banner() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp, start = 16.dp, end = 16.dp)
                .height(160.dp)
                .background(
                    color = Color(android.graphics.Color.parseColor("#521c98")),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            val (img, text, button) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.girl2),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(img) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)

                    }
            )
            Text(
                text = "اپلیکیشن کد \n دوره های برنامه نویسی \nمقدماتی و پیشرفته با مدرک ",
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Right,
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth(),
                style = TextStyle(
                    textDirection = TextDirection.Rtl
                )
            )
            Text(
                text = "همین الان شروع کن",
                fontSize = 14.sp ,
                fontWeight = FontWeight.SemiBold ,
                color = Color(android.graphics.Color.parseColor("#521c98")) ,
                modifier = Modifier
                    .padding(start = 16.dp , end = 16.dp)
                    .constrainAs(button){
                        top.linkTo(text.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }

                    .background(
                        Color(android.graphics.Color.parseColor("#f0e9fa"))
                        , shape = RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp)
                    ,
                style = TextStyle(
                    textDirection = TextDirection.Rtl
                )
            )

        }
    }

    @Composable
    fun SearchRow() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp, start = 16.dp, end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            var text by rememberSaveable { mutableStateOf("") }


            Image(
                painter = painterResource(id = R.drawable.bell),
                contentDescription = "null",
                modifier = Modifier.padding(16.dp)
            )

            TextField(
                value = text,
                onValueChange = { text = it },
                label = {
                    Text(
                        text = "...جستجو",
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Right, // تنظیم متن به راست‌چین
                        modifier = Modifier.fillMaxWidth() // تضمین فضای کامل برای متن
                    )
                },
                textStyle = TextStyle(
                    textDirection = TextDirection.Rtl, // تنظیم جهت متن به راست‌چین
                    textAlign = TextAlign.Right // راست‌چین کردن متن وارد شده
                ),
                leadingIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.search_icon),
                        contentDescription = "جستجو",
                        modifier = Modifier
                            .size(23.dp)
                    )
                },
                shape = RoundedCornerShape(10.dp),
                colors = androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color.White,
                    focusedLabelColor = Color.Transparent,
                    unfocusedLabelColor = Color.Transparent,
                    textColor = Color(android.graphics.Color.parseColor("#5e5e5e")),
                    unfocusedBorderColor = Color(android.graphics.Color.parseColor("#5e5e5e"))
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .border(
                        1.dp,
                        Color(android.graphics.Color.parseColor("#521C98")),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White, CircleShape)
                    .padding(end = 8.dp) // افزودن فضای مناسب به راست
            )


        }
    }

    @Preview(
        showBackground = true, showSystemUi = true,
        device = Devices.PIXEL_7_PRO
    )
    @Composable
    fun GreetingPreview() {

        Greeting()

    }

}


