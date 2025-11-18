import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class LocationHistoryScreen extends StatefulWidget {
  const LocationHistoryScreen({super.key});

  @override
  State<LocationHistoryScreen> createState() => _LocationHistoryScreenState();
}

class _LocationHistoryScreenState extends State<LocationHistoryScreen> {
  List<dynamic> _history = [];

  Future<void> fetchLocationHistory() async {
    final response = await http.get(Uri.parse('http://localhost:8080/api/location/history/1'));

    if (response.statusCode == 200) {
      setState(() {
        _history = jsonDecode(response.body);
      });
    }
  }

  @override
  void initState() {
    super.initState();
    fetchLocationHistory();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Location History')),
      body: ListView.builder(
        itemCount: _history.length,
        itemBuilder: (context, index) {
          final entry = _history[index];
          return ListTile(
            leading: const Icon(Icons.location_on, color: Colors.blue),
            title: Text(entry['location']),
            subtitle: Text(entry['timestamp']),
          );
        },
      ),
    );
  }
}
