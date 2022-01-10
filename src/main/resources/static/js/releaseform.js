function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var releaseformsApi = Vue.resource('/releaseform{/id}')

Vue.component('releaseform-form', {
    props: ['releaseforms', 'releaseformAttr'],
    data: function() {
        return {
            name: '',
            id: ''
        }
    },
    watch: {
        releaseformAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input style="margin:10px;" type="text" placeholder="Name" v-model="name" /><br />' +
            '<input style="margin:10px;" type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var releaseform = {
                name: this.name
             };

            if (this.id) {
                releaseformsApi.update({id: this.id}, releaseform).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.releaseforms, data.id);
                        this.releaseforms.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                releaseformsApi.save({}, releaseform).then(result =>
                    result.json().then(data => {
                        this.releaseforms.push(data);
                        this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('releaseform-row', {
    props: ['releaseform', 'editMethod', 'releaseforms'],
    template: '<tr>'+
            '<td>{{releaseform.name}}</td>'+
            '<td><input type="button" value="Edit" @click="edit" /></td>' +
            '<td><input type="button" value="X" @click="del" /></td>' +
        '</tr>',
    methods: {
            edit: function() {
                this.editMethod(this.releaseform);
            },
            del: function() {
                releaseformsApi.remove({id: this.releaseform.id}).then(result => {
                    if (result.ok) {
                        this.releaseforms.splice(this.releaseforms.indexOf(this.releaseform), 1)
                    }
                })
            }
        }
});

Vue.component('releaseforms-list', {
    props: ['releaseforms'],
    data: function() {
        return {
            releaseform: null
        }
      },
    template:
    '<div>' +
        '<releaseform-form :releaseforms="releaseforms" :releaseformAttr="releaseform" />' +
        '<table><tr><th>Name</th></tr>' +
            '<releaseform-row v-for="releaseform in releaseforms" :key="releaseform.id" :releaseform="releaseform" :editMethod="editMethod" :releaseforms="releaseforms"/>'+
        '</table>'+
    '</div>',
    created: function(){
        releaseformsApi.get().then(result =>
        result.json().then(data =>
                data.forEach(releaseform => this.releaseforms.push(releaseform))
            )
        )
    },
    methods: {
        editMethod: function(releaseform) {
            this.releaseform = releaseform;
        }
      }
});

var app = new Vue({
  el: '#app',
  template: '<releaseforms-list :releaseforms="releaseforms" />',
  data: {
    releaseforms: []
  }
});