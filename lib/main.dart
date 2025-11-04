import 'package:flutter/material.dart';
import 'screens/login_screen.dart';

void main() {
  runApp(const CampusFindaApp());
}

class CampusFindaApp extends StatelessWidget {
  const CampusFindaApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Campus Finda',
      theme: ThemeData(primarySwatch: Colors.blue),
      debugShowCheckedModeBanner: false,
      home: const LoginScreen(),
    );
  }
}
