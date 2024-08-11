package com.example.mindbenders.ui.theme.screens.clients

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.example.mindbenders.R

@Composable
fun UpdateClient(navController: NavController, clientId: String) {
    // States to hold client data
    var imageUri by rememberSaveable { mutableStateOf("") }
    var firstname by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var bio by remember { mutableStateOf("") }

    // Image picker launcher
    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri = it.toString() }
    }

    // Painter for the profile image
    val painter = rememberImagePainter(
        if (imageUri.isEmpty()) R.drawable.ic_photo else imageUri,
        builder = {
            crossfade(true)
            placeholder(R.drawable.ic_photo) // Placeholder image while loading
        }
    )

    // Dummy function to simulate data fetching
    LaunchedEffect(clientId) {
        // Simulate fetching data from a database or network
        // For example purposes, we're using static values
        firstname = "John"
        lastname = "Doe"
        gender = "Male"
        age = "30"
        bio = "Lorem ipsum dolor sit amet."
        imageUri = "" // Assuming there's a default or previously set image URI
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "UPDATE CLIENT",
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.Magenta)
        )
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { /* TODO: Handle navigation back */ }) {
                Text(text = "GO BACK")
            }
            Button(onClick = { /* TODO: Handle save/update */ }) {
                Text(text = "SAVE")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        // Profile Image
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(10.dp)
                .size(180.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { imageLauncher.launch("image/*") },
                contentScale = ContentScale.Crop
            )
        }
        Text(text = "Change Picture Here", textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(10.dp))

        // Input Fields
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(text = "Enter First Name") },
            placeholder = { Text(text = "Please Enter First Name") },
            value = firstname,
            onValueChange = { firstname = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(text = "Enter Last Name") },
            placeholder = { Text(text = "Please Enter Last Name") },
            value = lastname,
            onValueChange = { lastname = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(text = "Enter Your Gender") },
            placeholder = { Text(text = "Please Enter Your Gender") },
            value = gender,
            onValueChange = { gender = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            label = { Text(text = "Enter Your Age") },
            placeholder = { Text(text = "Please Enter Your Age") },
            value = age,
            onValueChange = { age = it }
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .padding(horizontal = 16.dp),
            label = { Text(text = "Enter Your Description") },
            placeholder = { Text(text = "Please Enter Your Bio") },
            value = bio,
            singleLine = false,
            onValueChange = { bio = it }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UpdateClientPreview() {
    // You might want to provide a NavController or use a mock
    UpdateClient(navController = rememberNavController(), clientId = "dummyId")
}
