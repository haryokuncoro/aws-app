package com.aws.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return  """
                <!DOCTYPE html>
                       <html lang="en">
                       <head>
                           <meta charset="UTF-8">
                           <meta name="viewport" content="width=device-width, initial-scale=1.0">
                           <title>AWS App Welcome</title>
                           <script src="https://cdn.tailwindcss.com"></script>
                       </head>
                       <body class="bg-gradient-to-br from-blue-900 to-gray-900 flex items-center justify-center min-h-screen p-6">
                           <div class="p-10 md:p-16 bg-white bg-opacity-5 rounded-3xl shadow-2xl border border-blue-500/50 backdrop-blur-md text-center transform hover:scale-[1.02] transition duration-300">
                               <h1 class="text-5xl md:text-6xl font-extrabold text-transparent bg-clip-text bg-gradient-to-r from-yellow-300 to-cyan-300 tracking-tighter">
                                   Welcome to AWS App
                               </h1>
                               <p class="mt-4 text-xl text-gray-300 font-light">Your cloud-powered dashboard awaits.</p>
                           </div>
                       </body>
                       </html>
            """;
    }
}
