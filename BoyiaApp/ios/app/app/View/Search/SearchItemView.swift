//
//  SearchItem.swift
//  app
//
//  Created by yanbo on 2022/7/11.
//

import SwiftUI

struct SearchItemView : View {
    private var item: BoyiaAppItem
    
    public init(item: BoyiaAppItem) {
        self.item = item;
    }
    var body: some View {
        HStack {
//            AsyncImage(url: URL(string: HttpUtil.getRemoteUrl(url: item.cover)!)!) { phase in
//                if let image = phase.image {
//                    image.resizable()
//                        .transition(.slide)
//                } else if phase.error != nil {
//                    ProgressView()
//                } else {
//                    ProgressView()
//                }
//            }
            marginLeft(left: 20.dp)
            BoyiaImage(url: self.item.cover, width: 108.dp, height: 108.dp)
                .cornerRadius(12.dp)
            marginLeft(left: 20.dp)
            VStack(alignment: .leading, spacing: 10.dp) {
                Text(item.name).foregroundColor(Color.black)
                Text("App ID: \(item.appId)").foregroundColor(Color.black)
            }
            Spacer()
            Button(action: {
                
            }) {
                ZStack {
                    Text("Install")
                        .font(.system(size: 24.dp))
                        .foregroundColor(Color.green)
                }.frame(width: 108.dp, height: 54.dp, alignment: Alignment.center)
            }
            .frame(width: 108.dp, height: 54.dp, alignment: Alignment.center)
            .cornerRadius(27.dp)
            .overlay {
                RoundedRectangle(cornerRadius: 27.dp, style: .continuous)
                    .stroke(Color.green, lineWidth: 2.dp)
            }
            marginLeft(left: 20.dp)
        }
        .frame(width: PixelRatio.screenWidth(), height: 108.dp)
    }
}
